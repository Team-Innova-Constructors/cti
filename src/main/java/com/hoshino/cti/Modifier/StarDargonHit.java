package com.hoshino.cti.Modifier;

import com.hoshino.cti.Entity.Projectiles.StarDargonAmmo;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class StarDargonHit extends Modifier implements MeleeHitModifierHook , MeleeDamageModifierHook {
    @Override
    public int getPriority() {
        return 10;
    }

    private static final Map<UUID, Float> DAMAGE_SHOULD_BE = new ConcurrentHashMap<>();
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT,ModifierHooks.MELEE_DAMAGE);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        var player=context.getPlayerAttacker();
        var target=context.getLivingTarget();
        if(player==null||target==null)return;
        float damageShouldBe=DAMAGE_SHOULD_BE.getOrDefault(player.getUUID(),10f);
        var ammo=new StarDargonAmmo(player,player.getLevel(),target.position(),damageShouldBe);
        player.getLevel().addFreshEntity(ammo);
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        var player=context.getPlayerAttacker();
        if(player!=null){
            DAMAGE_SHOULD_BE.put(player.getUUID(),damage);
        }
        return damage;
    }
}
