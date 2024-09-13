package com.hoshino.cti.Blocks.BlockEntity;

import com.hoshino.cti.Screen.menu.AtmosphereExtractorMenu;
import com.hoshino.cti.register.ctiBlockEntityType;
import com.hoshino.cti.util.Recipe.AtmosphereExtractor;
import com.hoshino.cti.util.ctiEnergyStore;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.hoshino.cti.util.BiomeUtil.getBiomeKey;

public class AtmosphereExtractorEntity extends BlockEntity implements MenuProvider {
    public AtmosphereExtractorEntity(BlockPos blockPos, BlockState blockState) {
        super(ctiBlockEntityType.Atmosphere_extractor.get(), blockPos, blockState);
        this.DATA = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0-> AtmosphereExtractorEntity.this.PROGRESS;
                    case 1-> AtmosphereExtractorEntity.this.MAX_PROGRESS;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0-> AtmosphereExtractorEntity.this.PROGRESS =value;
                    case 1-> AtmosphereExtractorEntity.this.MAX_PROGRESS =value;
                };
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }
    protected final ContainerData DATA;
    public int PROGRESS =0;
    public int MAX_PROGRESS =40;
    public Component DISPLAY_NAME =Component.translatable("cti.machine.atmosphere_extractor").withStyle(ChatFormatting.DARK_PURPLE);
    public final int MAX_ENERGY =75000000;
    public final int MAX_TRANSFER =7500000;
    public final int BASE_ENERGY_PERTICK =7500000;
    protected final int ITEM_SLOT_AMOUNT =1;



    @Nullable
    private final ctiEnergyStore ENERGY_STORAGE =getMaxEnergy()>=0? new ctiEnergyStore(getMaxEnergy(),getMaxTransfer()) {
        @Override
        public void onEnergyChange() {
            setChanged();
        }
    }:null;

    private final ItemStackHandler itemStackHandler =new ItemStackHandler(getSlotAmount()){
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    public int getSlotAmount(){
        return this.ITEM_SLOT_AMOUNT;
    }
    public int getMaxEnergy(){
        return this.MAX_ENERGY;
    }
    public int getMaxTransfer(){
        return this.MAX_TRANSFER;
    }
    public int getMaxProgress(){
        return this.MAX_PROGRESS;
    }
    public int getEnergyPerTick(){
        return this.BASE_ENERGY_PERTICK;
    }



    private LazyOptional<ItemStackHandler> LazyitemStackHandler = LazyOptional.empty();
    private LazyOptional<IEnergyStorage> LazyenergyHandler = LazyOptional.empty();

    public Component getDisplayName() {
        return DISPLAY_NAME;
    }



    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> capability){
        if (capability == ForgeCapabilities.ITEM_HANDLER){
            return LazyitemStackHandler.cast();
        }
        if (capability == ForgeCapabilities.ENERGY&&this.MAX_ENERGY>0){
            return LazyenergyHandler.cast();
        }
        return super.getCapability(capability);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        LazyitemStackHandler = LazyOptional.of(()->itemStackHandler);
        LazyenergyHandler = LazyOptional.of(()->ENERGY_STORAGE);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        LazyitemStackHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        if (itemStackHandler.getSlots()>0) {
            nbt.put("inventory", itemStackHandler.serializeNBT());
        }
        if (ENERGY_STORAGE != null) {
            nbt.put("cti_machine.energy_store",ENERGY_STORAGE.serializeNBT());
        }

        nbt.putInt("cti_machine.progerss",this.PROGRESS);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
        this.PROGRESS =nbt.getInt("cti_machine.progerss");
        super.load(nbt);
    }

    public void dropItem(){
        if (itemStackHandler.getSlots()>0) {
            SimpleContainer container = new SimpleContainer(itemStackHandler.getSlots());
            for (int i = 0; i < itemStackHandler.getSlots(); i++) {
                container.setItem(i, itemStackHandler.getStackInSlot(i));
            }
            if (this.level != null) {
                Containers.dropContents(this.level, this.worldPosition, container);
            }
        }
    }

    @Nullable
    public ctiEnergyStore getEnergyStorage(){
        return ENERGY_STORAGE;
    }


    public static void tick(Level level, BlockPos blockPos, BlockState state, AtmosphereExtractorEntity entity) {
        ResourceKey<Biome> biomekey =getBiomeKey(level.getBiome(blockPos));
        SimpleContainer container = new SimpleContainer(1);
        container.setItem(0,entity.itemStackHandler.getStackInSlot(0));
        if (biomekey == null){
            return;
        }
        ItemStack output =AtmosphereExtractor.BiomeToItem.getOutput(biomekey);
        if (output.isEmpty()){
            return;
        }
        if (!canOutput(container,output)){
            return;
        }
        int Speed=0;
        BlockPos pos =blockPos;
        for (int i=0;i<4;i++){
            pos =pos.above();
            BlockState blockstate = level.getBlockState(pos);
            if (!(blockstate.is(Blocks.AIR) || blockstate.is(Blocks.CAVE_AIR) || blockstate.is(Blocks.VOID_AIR) )){
                return;
            }
            else {
                if (!level.isClientSide) {
                    ((ServerLevel) level).sendParticles(ParticleTypes.DOLPHIN, pos.getX()+0.5, pos.getY(), pos.getZ()+0.5, 1, 0.25, 0.25, 0.25, 0.05);
                }
                Speed++;
            }
        }
        entity.PROGRESS= Mth.clamp(entity.PROGRESS+Speed,0,entity.MAX_PROGRESS);
        if (entity.PROGRESS==entity.MAX_PROGRESS){
            entity.PROGRESS=0;
            if (!container.getItem(0).isEmpty()){
                output.setCount(output.getCount()+container.getItem(0).getCount());
            }
            entity.itemStackHandler.setStackInSlot(0,output);
        }

    }

    public static boolean canOutput(SimpleContainer container,ItemStack stack){
        return container.isEmpty() || (container.getItem(0).is(stack.getItem())&&container.getItem(0).getCount()+stack.getCount()<=64);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new AtmosphereExtractorMenu(i,inventory,this,this.DATA);
    }




}
