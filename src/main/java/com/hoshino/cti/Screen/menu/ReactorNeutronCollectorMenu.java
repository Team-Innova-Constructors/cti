package com.hoshino.cti.Screen.menu;

import com.hoshino.cti.Blocks.BlockEntity.GeneralMachineEntity;
import com.hoshino.cti.Blocks.BlockEntity.ReactorNeutronCollectorEntity;
import com.hoshino.cti.register.ctiBlock;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class ReactorNeutronCollectorMenu extends GeneralMachineMenu {
    public final ReactorNeutronCollectorEntity entity;
    private final Level level;
    private final ContainerData data;



    protected ReactorNeutronCollectorMenu(int id , Inventory inventory, FriendlyByteBuf buf) {
        this(id, inventory,inventory.player.level.getBlockEntity(buf.readBlockPos()),new SimpleContainerData(4));
    }
    public ReactorNeutronCollectorMenu(int id, Inventory inventory, BlockEntity entity, ContainerData data) {
        super(ctiMenu.NEUT_COL_MENU.get(), id,(GeneralMachineEntity) entity);
        checkContainerSize(inventory,1);
        this.level = inventory.player.level;
        this.entity = (ReactorNeutronCollectorEntity) entity;
        this.data =data;

        this.entity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler,0,84,35));
        });

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        addDataSlots(data);
    }



    public boolean isCrafting(){
        return data.get(0)>0;
    }

    public int getEnergyBarScale(){
        float energyStored =this.getEnergy();
        float maxEnergyStored =entity.getMaxEnergy();
        int barSize = 60;
        return energyStored!=0&&maxEnergyStored!=0? Mth.clamp((int)( barSize*energyStored/maxEnergyStored),0,60):0;
    }
    public float getProgress(){
        float progress = data.get(0);
        float maxprogress =data.get(1);
        return 100*progress/maxprogress;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slot0) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slot0);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (slot0 != 0) {
                if (slot0 >= 0 && slot0 < 24) {
                    if (!this.moveItemStackTo(itemstack1, 25, 33, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slot0 >= 24 && slot0 < 33 && !this.moveItemStackTo(itemstack1, 0, 25, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 33, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level,entity.getBlockPos()),player, ctiBlock.reactor_neutron_collector.get());
    }
    private void addPlayerInventory(Inventory inventory){
        for (int i=0;i<3;i++){
            for (int j=0;j<9;j++){
                this.addSlot(new Slot(inventory,j+9*i+9,j*18+8,i*18+84));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory){
        for (int i=0;i<9;i++){
            this.addSlot(new Slot(inventory,i,8+i*18,142));
        }
    }
}
