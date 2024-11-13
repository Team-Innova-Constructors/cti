package com.hoshino.cti.Modifier.Replace;

import com.gjhi.tinkersinnovation.register.TinkersInnovationModifiers;
import com.hoshino.cti.register.ctiModifiers;
import com.hoshino.cti.register.ctiSlots;
import com.james.tinkerscalibration.Utils;
import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import com.marth7th.solidarytinker.util.compound.icefantasy;
import com.xiaoyue.tinkers_ingenuity.register.TIModifiers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.*;
import tcintegrations.items.TCIntegrationsModifiers;

import java.util.List;

public class DeepOceanEcho extends BattleModifier {
    @Override
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if(attacker instanceof Player player){
            ItemStack Tool=player.getMainHandItem();
            int SEA= ModifierUtil.getModifierLevel(Tool, TIModifiers.SEA_DREAM.getId())+ModifierUtil.getModifierLevel(Tool, TCIntegrationsModifiers.WATER_POWERED_MODIFIER.getId())
                    +ModifierUtil.getModifierLevel(Tool, TIModifiers.DIVISION.getId())+ModifierUtil.getModifierLevel(Tool, TinkersInnovationModifiers.poseidite_weapon.getId())+ModifierUtil.getModifierLevel(Tool,Utils.hydrophilous.getId());
            float a = (Math.max(player.getMaxHealth() * 0.2f,1) * Math.max(player.getArmorValue() * 0.3f,1) *Math.max(player.totalExperience * 0.0001f,1))*0.1f*level*Math.max(SEA * 0.2F,1)*(1+ModifierUtil.getModifierLevel(Tool, ctiModifiers.DEEP_AND_DEEP_STATIC_MODIFIER.getId()));
            if(livingTarget instanceof Player){
                return damage * 0f;
            }
            else return damage+(a * 0.5f * level);
        }
        return damage;
    }

    @Override
    public void arrowhurt(ModifierNBT modifiers, NamespacedNBT persistentData, int level, Projectile projectile, EntityHitResult hit, AbstractArrow arrow, LivingEntity attacker, LivingEntity target) {
        if(attacker instanceof Player player){
            ItemStack Tool=player.getMainHandItem();
            int SEA= ModifierUtil.getModifierLevel(Tool, TIModifiers.SEA_DREAM.getId())+ModifierUtil.getModifierLevel(Tool, TCIntegrationsModifiers.WATER_POWERED_MODIFIER.getId())
                    +ModifierUtil.getModifierLevel(Tool, TIModifiers.DIVISION.getId())+ModifierUtil.getModifierLevel(Tool, TinkersInnovationModifiers.poseidite_weapon.getId())+ModifierUtil.getModifierLevel(Tool,Utils.hydrophilous.getId());
            float a = (Math.max(player.getMaxHealth() * 0.2f,1) * Math.max(player.getArmorValue() * 0.3f,1) *Math.max(player.totalExperience * 0.0001f,1))*0.1f*level*Math.max(SEA * 0.2F,1)*(1+ModifierUtil.getModifierLevel(Tool, ctiModifiers.DEEP_AND_DEEP_STATIC_MODIFIER.getId()));
            if(target instanceof Player){
                arrow.setBaseDamage(0);
            }else arrow.setBaseDamage(arrow.getBaseDamage() + (a * 0.5 * level));
        }
    }

    @Override
    public void addVolatileData(IToolContext iToolContext, @NotNull ModifierEntry modifierEntry, ModDataNBT modDataNBT) {
        modDataNBT.addSlots(ctiSlots.OCEAN,5);
        modDataNBT.addSlots(ctiSlots.SHENJIN,5);
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> list, TooltipKey key, TooltipFlag tooltipFlag) {
        if (player != null) {
            ItemStack Tool=player.getMainHandItem();
            int level = modifier.getLevel();
            int SEA= ModifierUtil.getModifierLevel(Tool, TIModifiers.SEA_DREAM.getId())+ModifierUtil.getModifierLevel(Tool, TCIntegrationsModifiers.WATER_POWERED_MODIFIER.getId())
                    +ModifierUtil.getModifierLevel(Tool, TIModifiers.DIVISION.getId())+ModifierUtil.getModifierLevel(Tool, TinkersInnovationModifiers.poseidite_weapon.getId())+ModifierUtil.getModifierLevel(Tool,Utils.hydrophilous.getId());
            float a = (Math.max(player.getMaxHealth() * 0.2f,1) * Math.max(player.getArmorValue() * 0.6f,1) *Math.max(player.totalExperience * 0.0001f,1))*0.f*level*Math.max(SEA * 0.2F,1);
            list.add(applyStyle(Component.literal(icefantasy.GetColor("当前回声点数")).append(icefantasy.GetColor(a + ""))));
            list.add(applyStyle(Component.literal(icefantasy.GetColor("额外生效的词条等级")).append(icefantasy.GetColor(SEA + ""))));
            list.add(applyStyle(Component.literal(icefantasy.GetColor("每点回声所增幅的伤害")).append(icefantasy.GetColor(level * 0.1f*Math.max(SEA * 0.2F,1) +"攻击力"))));
            list.add(applyStyle(Component.literal(icefantasy.GetColor("实际提升的总伤害")).append(icefantasy.GetColor((level * 0.1f)*Math.max(SEA * 0.2F,1) * a + "攻击力"))));
        }
    }
}

