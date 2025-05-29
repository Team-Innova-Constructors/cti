package com.hoshino.cti.Modifier;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class test extends Modifier implements InventoryTickModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder builder) {
        super.registerHooks(builder);
        builder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }
    public static void applyMobEffect(LivingEntity holder, MobEffect effect){
        if(holder instanceof Player player){
        }
    }
    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        var MOBEFFECT= ForgeRegistries.MOB_EFFECTS;
        var level=holder.level;


        MOBEFFECT.getValues().stream().filter(mobEffect ->
                        Optional.ofNullable(MOBEFFECT.getKey(mobEffect))
                                .map(key-> key.getNamespace().equals("cti")).orElse(false))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list->list.isEmpty()?Optional.<MobEffect>empty():Optional.of(list.get(level.random.nextInt(list.size())))
                )).ifPresent(mobEffect -> applyMobEffect(holder,mobEffect));


        var collection=MOBEFFECT.getValues().stream().filter(mobEffect -> mobEffect.isBeneficial()&&Optional.ofNullable(MOBEFFECT.getKey(mobEffect))
                .map(key-> key.getNamespace().equals("cti"))
                .orElse(false))
                .toList();
        collection.stream().skip(level.random.nextInt(collection.size())).findFirst().ifPresent(mobEffect -> applyMobEffect(holder,mobEffect));


        var collections=MOBEFFECT.getValues().stream().filter(mobEffect -> {
            var key=MOBEFFECT.getKey(mobEffect);
            return key!=null&&key.getNamespace().equals("cti");
        }).toList();
        collections.stream().skip(level.random.nextInt(collections.size())).findFirst().ifPresent(mobEffect -> applyMobEffect(holder,mobEffect));


        List<MobEffect> correctMobEffect=new ArrayList<>();
        var collectionss=MOBEFFECT.getValues();
        for(MobEffect mobEffect:collectionss){
            var key=MOBEFFECT.getKey(mobEffect);
            if(key==null||!key.getNamespace().equals("cti"))continue;
            correctMobEffect.add(mobEffect);
        }
        int number= holder.level.random.nextInt(collections.size());
        applyMobEffect(holder,correctMobEffect.get(number));
    }
}
