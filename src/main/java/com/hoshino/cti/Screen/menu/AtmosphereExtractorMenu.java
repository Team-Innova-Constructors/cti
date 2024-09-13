package com.hoshino.cti.Screen.menu;

import com.hoshino.cti.Blocks.BlockEntity.AtmosphereExtractorEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class AtmosphereExtractorMenu extends AbstractContainerMenu {
    public final AtmosphereExtractorEntity entity;
    private final Level level;
    private final ContainerData data;

    protected AtmosphereExtractorMenu(int id , Inventory inventory, FriendlyByteBuf buf) {
        this(id, inventory,inventory.player.level.getBlockEntity(buf.readBlockPos()),new SimpleContainerData(2));
    }
    public AtmosphereExtractorMenu(int id, Inventory inventory, BlockEntity entity, ContainerData data) {
        super(ctiMenu.ATMOSPHERE_EXT_MENU.get(), id);
        checkContainerSize(inventory,1);
        this.level = inventory.player.level;
        this.entity = (AtmosphereExtractorEntity) entity;
        this.data =data;

        this.entity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler,0,116,35));
        });

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        addDataSlots(data);
    }

    public boolean isCrafting(){
        return data.get(0)>0;
    }

    public int getProgressScale(){
        int progress = data.get(0);
        int maxProgress =data.get(1);
        int barSize =26;
        return maxProgress!=0&&progress!=0? barSize*progress/maxProgress:0;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slot0) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(slot0);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (slot0 == 2) {
                if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            }
        }
        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level,entity.getBlockPos()),player,entity.getBlockState().getBlock());
    }
    private void addPlayerInventory(Inventory inventory){
        for (int i=0;i<3;i++){
            for (int j=0;j<9;j++){
                this.addSlot(new Slot(inventory,j+9*i+9,j*18+8,i*18+86));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory){
        for (int i=0;i<9;i++){
            this.addSlot(new Slot(inventory,i,8+i*18,144));
        }
    }
}
