package com.hoshino.cti.Modifier;

import com.c2h6s.etshtinker.Entities.plasmawaveslashentity;
import com.c2h6s.etshtinker.Modifiers.modifiers.etshmodifieriii;
import com.c2h6s.etshtinker.init.etshtinkerEntity;
import com.c2h6s.etshtinker.util.vecCalc;
import com.hoshino.cti.netwrok.ctiPacketHandler;
import com.hoshino.cti.netwrok.packet.PPlasmaWaveSlashC2S;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.LogicalSide;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class PlasmaWaveSlashPlus extends etshmodifieriii {

    public PlasmaWaveSlashPlus() {
        MinecraftForge.EVENT_BUS.addListener(this::LeftClick);
        MinecraftForge.EVENT_BUS.addListener(this::LeftClickBlock);
    }

    @Override
    public boolean isNoLevels() {
        return true;
    }

    private void LeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getSide() == LogicalSide.SERVER) {
            Player player = event.getEntity();
            ItemStack stack = player.getItemInHand(event.getHand());
            if (stack.getItem() instanceof IModifiable) {
                if (ToolStack.from(stack).getModifierLevel(this) > 0)
                    createslash(event.getEntity(), ToolStack.from(stack));
            }
        }
    }

    private void LeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        if (stack.getItem() instanceof IModifiable) {
            if (ToolStack.from(stack).getModifierLevel(this) > 0)
                ctiPacketHandler.sendToServer(new PPlasmaWaveSlashC2S());
        }
    }

    @Override
    public float beforeMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
        if (!context.isExtraAttack() && context.isFullyCharged() && context.getAttacker() instanceof Player player) {
            createslash(player, tool);
        }
        return knockback;
    }

    public void createslash(Player player, IToolStackView tool) {
        if (player != null && player.getAttackStrengthScale(0) >= 0.8) {
            Level world = player.level;
            plasmawaveslashentity slash = new plasmawaveslashentity(etshtinkerEntity.plasmawaveslashEntity.get(), world);
            world.noCollision(slash);
            slash.noCulling = true;
            slash.setOwner(player);
            slash.setPos(player.getX(), player.getY() + 0.5 * (double) player.getBbHeight(), player.getZ());
            slash.tool = tool;
            Vec3 vec3 = vecCalc.getUnitizedVec3(player.getLookAngle());
            slash.setDeltaMovement(vec3.scale(5.0));
            world.addFreshEntity(slash);
        }
    }

    public static void createSlash(Player player, IToolStackView tool) {
        if (player != null && player.getAttackStrengthScale(0) >= 0.8) {
            Level world = player.level;
            plasmawaveslashentity slash = new plasmawaveslashentity(etshtinkerEntity.plasmawaveslashEntity.get(), world);
            world.noCollision(slash);
            slash.noCulling = true;
            slash.setOwner(player);
            slash.setPos(player.getX(), player.getY() + 0.5 * (double) player.getBbHeight(), player.getZ());
            slash.tool = tool;
            Vec3 vec3 = vecCalc.getUnitizedVec3(player.getLookAngle());
            slash.setDeltaMovement(vec3.scale(5.0));
            world.addFreshEntity(slash);
        }
    }
}
