package com.hoshino.cti.Modifier;

import com.c2h6s.etshtinker.util.slotUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.c2h6s.etshtinker.etshtinker.EtSHrnd;

public class All extends NoLevelsModifier implements ToolDamageModifierHook {
    public static List<MobEffect> ls =new ArrayList<>(List.of());
    public static void init(){
        Iterator<MobEffect> iterator =ForgeRegistries.MOB_EFFECTS.iterator();
        if (iterator.hasNext()){
            MobEffect effect =iterator.next();
            if (effect!=null&& effect.getCategory()== MobEffectCategory.HARMFUL) {
                ls.add(effect);
            }
        }
    }
    public All(){
        MinecraftForge.EVENT_BUS.addListener(this::livingHurtEvent);
        MinecraftForge.EVENT_BUS.addListener(this::livingDamageEvent);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOL_DAMAGE);
    }

    private void livingDamageEvent(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player&&event.getSource().getEntity()!=player) {
            for (EquipmentSlot slot : slotUtil.ALL) {
                ItemStack stack = player.getItemBySlot(slot);
                if (stack.getItem() instanceof IModifiable) {
                    ToolStack tool = ToolStack.from(stack);
                    if (tool.getModifierLevel(this) > 0 ) {
                        event.setAmount(event.getAmount()/4);
                        if (event.getSource().getEntity() instanceof LivingEntity living){
                            CompoundTag tag = living.getPersistentData();
                            tag.putBoolean("vulnerable",true);
                            if (!tag.contains("dmg_amplifier")){
                                tag.putFloat("dmg_amplifier",1.5f);
                            }
                            else {
                                tag.putFloat("dmg_amplifier",Math.max(1.5f,tag.getFloat("dmg_amplifier")+0.5f));
                            }

                            if (!tag.contains("legacyhealth")){
                                tag.putFloat("legacyhealth",event.getEntity().getHealth()-event.getAmount());
                            }
                            else {
                                if (event.getEntity().getHealth()>tag.getFloat("legacyhealth")){
                                    event.getEntity().setHealth(tag.getFloat("legacyhealth"));
                                }
                                tag.putFloat("legacyhealth",tag.getFloat("legacyhealth")-event.getAmount());
                            }

                            if (!tag.contains("dmg_amplifier")){
                                tag.putFloat("dmg_amplifier",1.5f);
                            }
                            else {
                                tag.putFloat("atomic_dec",Math.max(20,tag.getFloat("atomic_dec")+20));
                            }

                            if (!tag.contains("dmg_amplifier")){
                                tag.putFloat("dmg_amplifier",1.5f);
                            }
                            else {
                                tag.putFloat("quark_disassemble",Math.max(20,tag.getFloat("quark_disassemble")+20));
                            }
                            if (ls!=null&&!ls.isEmpty()){
                                int i =0;
                                while (i<10){
                                    MobEffect effect = ls.get(EtSHrnd().nextInt(ls.size()));
                                    living.addEffect(new MobEffectInstance(effect,200,9,false,false));
                                    i++;
                                }
                            }
                        }
                    }
                }
            }
            LazyOptional<IItemHandlerModifiable> optional = CuriosApi.getCuriosHelper().getEquippedCurios(player);
            if (optional.isPresent()){
                IItemHandlerModifiable handler =optional.orElse(null);
                for (int i =0;i<handler.getSlots();i++){
                    ItemStack stack =handler.getStackInSlot(i);
                    if (stack.getItem() instanceof IModifiable) {
                        ToolStack tool = ToolStack.from(stack);
                        if (tool.getModifierLevel(this) > 0 ) {
                            event.setAmount(event.getAmount()/4);
                            if (event.getSource().getEntity() instanceof LivingEntity living){
                                CompoundTag tag = living.getPersistentData();
                                tag.putBoolean("vulnerable",true);
                                if (!tag.contains("dmg_amplifier")){
                                    tag.putFloat("dmg_amplifier",1.5f);
                                }
                                else {
                                    tag.putFloat("dmg_amplifier",Math.max(1.5f,tag.getFloat("dmg_amplifier")+0.5f));
                                }

                                if (!tag.contains("legacyhealth")){
                                    tag.putFloat("legacyhealth",event.getEntity().getHealth()-event.getAmount());
                                }
                                else {
                                    if (event.getEntity().getHealth()>tag.getFloat("legacyhealth")){
                                        event.getEntity().setHealth(tag.getFloat("legacyhealth"));
                                    }
                                    tag.putFloat("legacyhealth",tag.getFloat("legacyhealth")-event.getAmount());
                                }

                                if (!tag.contains("dmg_amplifier")){
                                    tag.putFloat("dmg_amplifier",1.5f);
                                }
                                else {
                                    tag.putFloat("atomic_dec",Math.max(20,tag.getFloat("atomic_dec")+20));
                                }

                                if (!tag.contains("dmg_amplifier")){
                                    tag.putFloat("dmg_amplifier",1.5f);
                                }
                                else {
                                    tag.putFloat("quark_disassemble",Math.max(20,tag.getFloat("quark_disassemble")+20));
                                }
                                if (ls!=null&&!ls.isEmpty()){
                                    int j =0;
                                    while (j<10){
                                        MobEffect effect = ls.get(EtSHrnd().nextInt(ls.size()));
                                        living.forceAddEffect(new MobEffectInstance(effect,200,9,false,false),player);
                                        j++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void livingHurtEvent(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player&&event.getEntity()!=player){
            for (EquipmentSlot slot: slotUtil.ALL){
                ItemStack stack = player.getItemBySlot(slot);
                if (stack.getItem() instanceof IModifiable){
                    ToolStack tool = ToolStack.from(stack);
                    if (tool.getModifierLevel(this)>0&&event.getEntity()!=null){
                        event.setAmount(event.getAmount()*2);
                        CompoundTag tag = event.getEntity().getPersistentData();
                        tag.putBoolean("vulnerable",true);
                        if (!tag.contains("dmg_amplifier")){
                            tag.putFloat("dmg_amplifier",1.5f);
                        }
                        else {
                            tag.putFloat("dmg_amplifier",Math.max(1.5f,tag.getFloat("dmg_amplifier")+0.5f));
                        }

                        if (!tag.contains("legacyhealth")){
                            tag.putFloat("legacyhealth",event.getEntity().getHealth()-event.getAmount());
                        }
                        else {
                            if (event.getEntity().getHealth()>tag.getFloat("legacyhealth")){
                                event.getEntity().setHealth(tag.getFloat("legacyhealth"));
                            }
                            tag.putFloat("legacyhealth",tag.getFloat("legacyhealth")-event.getAmount());
                        }

                        if (!tag.contains("dmg_amplifier")){
                            tag.putFloat("dmg_amplifier",1.5f);
                        }
                        else {
                            tag.putFloat("atomic_dec",Math.max(20,tag.getFloat("atomic_dec")+20));
                        }

                        if (!tag.contains("dmg_amplifier")){
                            tag.putFloat("dmg_amplifier",1.5f);
                        }
                        else {
                            tag.putFloat("quark_disassemble",Math.max(20,tag.getFloat("quark_disassemble")+20));
                        }
                        if (ls!=null&&!ls.isEmpty()){
                            int i =0;
                            while (i<10){
                                MobEffect effect = ls.get(EtSHrnd().nextInt(ls.size()));
                                event.getEntity().forceAddEffect(new MobEffectInstance(effect,200,9,false,false),player);
                                i++;
                            }
                        }
                    }
                }
            }
            LazyOptional<IItemHandlerModifiable> optional = CuriosApi.getCuriosHelper().getEquippedCurios(player);
            if (optional.isPresent()){
                IItemHandlerModifiable handler =optional.orElse(null);
                for (int i =0;i<handler.getSlots();i++){
                    ItemStack stack =handler.getStackInSlot(i);
                    if (stack.getItem() instanceof IModifiable) {
                        ToolStack tool = ToolStack.from(stack);
                        if (tool.getModifierLevel(this) > 0 ) {
                            event.setAmount(event.getAmount()*2);
                            if (tool.getModifierLevel(this)>0&&event.getEntity()!=null){
                                CompoundTag tag = event.getEntity().getPersistentData();
                                tag.putBoolean("vulnerable",true);
                                if (!tag.contains("dmg_amplifier")){
                                    tag.putFloat("dmg_amplifier",1.5f);
                                }
                                else {
                                    tag.putFloat("dmg_amplifier",Math.max(1.5f,tag.getFloat("dmg_amplifier")+0.5f));
                                }

                                if (!tag.contains("legacyhealth")){
                                    tag.putFloat("legacyhealth",event.getEntity().getHealth()-event.getAmount());
                                }
                                else {
                                    if (event.getEntity().getHealth()>tag.getFloat("legacyhealth")){
                                        event.getEntity().setHealth(tag.getFloat("legacyhealth"));
                                    }
                                    tag.putFloat("legacyhealth",tag.getFloat("legacyhealth")-event.getAmount());
                                }

                                if (!tag.contains("dmg_amplifier")){
                                    tag.putFloat("dmg_amplifier",1.5f);
                                }
                                else {
                                    tag.putFloat("atomic_dec",Math.max(20,tag.getFloat("atomic_dec")+20));
                                }

                                if (!tag.contains("dmg_amplifier")){
                                    tag.putFloat("dmg_amplifier",1.5f);
                                }
                                else {
                                    tag.putFloat("quark_disassemble",Math.max(20,tag.getFloat("quark_disassemble")+20));
                                }
                                if (ls!=null&&!ls.isEmpty()){
                                    int j =0;
                                    while (j<10){
                                        MobEffect effect = ls.get(EtSHrnd().nextInt(ls.size()));
                                        event.getEntity().forceAddEffect(new MobEffectInstance(effect,200,9,false,false),player);
                                        j++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getPriority() {
        return 512;
    }

    @Override
    public int onDamageTool(IToolStackView iToolStackView, ModifierEntry modifierEntry, int i, @Nullable LivingEntity livingEntity) {
        return 0;
    }
}
