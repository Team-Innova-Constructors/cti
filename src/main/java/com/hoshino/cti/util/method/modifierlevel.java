package com.hoshino.cti.util.method;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;


public class modifierlevel {

    //一个一个获取词条等级太麻烦了，所以我仿照晓月弄了这个


    public static int getmainhandmodifierlevel(LivingEntity entity, ModifierId modifierId) {
        if (entity != null) {
            if(entity instanceof Player player){
                ToolStack toolStack = ToolStack.from(entity.getItemBySlot(EquipmentSlot.MAINHAND));
                if (!toolStack.isBroken()) {
                    return ModifierUtil.getModifierLevel(entity.getItemBySlot(EquipmentSlot.MAINHAND), modifierId);
                }
            }
        }
        return 0;
    }
    public static int getoffhandmodifierlevel(LivingEntity entity, ModifierId modifierId) {
        if (entity != null) {
            if(entity instanceof Player player){
                ToolStack toolStack = ToolStack.from(entity.getItemBySlot(EquipmentSlot.OFFHAND));
                if (!toolStack.isBroken()) {
                    return ModifierUtil.getModifierLevel(entity.getItemBySlot(EquipmentSlot.OFFHAND), modifierId);
                }
            }
        }
        return 0;
    }
    public static int geteachhandstotalmodifierlevel(LivingEntity entity, ModifierId modifierId) {
        if (entity != null) {
            if(entity instanceof Player player){
                ToolStack toolStack = ToolStack.from(entity.getItemBySlot(EquipmentSlot.MAINHAND));
                if (!toolStack.isBroken()) {
                    return ModifierUtil.getModifierLevel(entity.getItemBySlot(EquipmentSlot.MAINHAND), modifierId)+ModifierUtil.getModifierLevel(entity.getItemBySlot(EquipmentSlot.OFFHAND), modifierId);
                }
            }
        }
        return 0;
    }
    public static boolean eachhandshavelevel(LivingEntity entity, ModifierId modifierId) {
        return modifierlevel.getmainhandmodifierlevel(entity, modifierId) > 0&& modifierlevel.getoffhandmodifierlevel(entity, modifierId) > 0;
    }
    public static boolean handshavelevel(LivingEntity entity, ModifierId modifierId) {
        return modifierlevel.geteachhandstotalmodifierlevel(entity, modifierId) > 0;
    }
    
    public static int getheadmodifierlevel(LivingEntity entity, ModifierId modifierId) {
        if (entity != null) {
            if(entity instanceof Player player){
                ToolStack toolStack = ToolStack.from(entity.getItemBySlot(EquipmentSlot.HEAD));
                if (!toolStack.isBroken()) {
                return ModifierUtil.getModifierLevel(entity.getItemBySlot(EquipmentSlot.HEAD), modifierId);
                }
            }
        }
        return 0;
    }
    public static int getchestmodifierlevel(LivingEntity entity, ModifierId modifierId) {
        if (entity != null) {
            if(entity instanceof Player player){
                ToolStack toolStack = ToolStack.from(entity.getItemBySlot(EquipmentSlot.CHEST));
                if (!toolStack.isBroken()) {
                    return ModifierUtil.getModifierLevel(entity.getItemBySlot(EquipmentSlot.CHEST), modifierId);
                }
            }
        }
        return 0;
    }
    public static int getlegsmodifierlevel(LivingEntity entity, ModifierId modifierId) {
        if (entity != null) {
            if(entity instanceof Player player){
                ToolStack toolStack = ToolStack.from(entity.getItemBySlot(EquipmentSlot.LEGS));
                if (!toolStack.isBroken()) {
                return ModifierUtil.getModifierLevel(entity.getItemBySlot(EquipmentSlot.LEGS), modifierId);
                }
            }
        }
        return 0;
    }
    public static int getfeetmodifierlevel(LivingEntity entity, ModifierId modifierId) {
        if (entity != null) {
            if(entity instanceof Player player){
                ToolStack toolStack = ToolStack.from(entity.getItemBySlot(EquipmentSlot.FEET));
                if (!toolStack.isBroken()) {
                    return ModifierUtil.getModifierLevel(entity.getItemBySlot(EquipmentSlot.FEET), modifierId);
                }
            }
        }
        return 0;
    }
    public static int getsinglearmorlevel(LivingEntity entity, ModifierId modifierId) {
        return modifierlevel.getheadmodifierlevel(entity, modifierId) + modifierlevel.getchestmodifierlevel(entity, modifierId) + modifierlevel.getlegsmodifierlevel(entity, modifierId) + modifierlevel.getfeetmodifierlevel(entity, modifierId);
    }
    public static boolean allarmorhaslevel(LivingEntity entity, ModifierId modifierId) {
        return modifierlevel.getheadmodifierlevel(entity, modifierId) > 0 && modifierlevel.getchestmodifierlevel(entity, modifierId) > 0 && modifierlevel.getlegsmodifierlevel(entity, modifierId) > 0 && modifierlevel.getfeetmodifierlevel(entity, modifierId) > 0;
    }
}
