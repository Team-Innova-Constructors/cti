package com.hoshino.cti.Blocks.BlockEntity;

import com.hoshino.cti.util.ctiEnergyStore;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class GeneralMachineEntity extends BlockEntity implements MenuProvider {
    public GeneralMachineEntity(BlockEntityType<?> entityType, BlockPos blockPos, BlockState blockState, int maxProgress, Component displayName,int itemSlotAmount,int maxEnergy,int maxTransfer,int baseEnergyPerTick) {
        super(entityType, blockPos, blockState);

        this.MAX_PROGRESS =maxProgress;
        this.DISPLAY_NAME =displayName;
        this.MAX_TRANSFER =maxTransfer;
        this.MAX_ENERGY =maxEnergy;
        this.BASE_ENERGY_PERTICK =baseEnergyPerTick;
        this.ITEM_SLOT_AMOUNT =itemSlotAmount;
        this.DATA = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0-> GeneralMachineEntity.this.PROGRESS;
                    case 1-> GeneralMachineEntity.this.MAX_PROGRESS;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0-> GeneralMachineEntity.this.PROGRESS =value;
                    case 1-> GeneralMachineEntity.this.MAX_PROGRESS =value;
                };
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Nullable
    private final ctiEnergyStore ENERGY_STORAGE =this.MAX_ENERGY>=0? new ctiEnergyStore(this.MAX_ENERGY,this.MAX_TRANSFER) {
        @Override
        public void onEnergyChange() {
            setChanged();
        }
    }:null;

    private ItemStackHandler itemStackHandler =new ItemStackHandler(this.ITEM_SLOT_AMOUNT){
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    protected ContainerData DATA;
    private int PROGRESS =0;
    private int MAX_PROGRESS;
    private Component DISPLAY_NAME;
    private int MAX_ENERGY;
    private int MAX_TRANSFER;
    private int BASE_ENERGY_PERTICK;
    private int ITEM_SLOT_AMOUNT;


    private LazyOptional<ItemStackHandler> LazyitemStackHandler = LazyOptional.empty();
    private LazyOptional<IEnergyStorage> LazyenergyHandler = LazyOptional.empty();

    @Override
    public Component getDisplayName() {
        return DISPLAY_NAME;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return null;
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


}
