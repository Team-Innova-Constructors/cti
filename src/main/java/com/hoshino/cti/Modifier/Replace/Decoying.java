package com.hoshino.cti.Modifier.Replace;
import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.FakePlayer;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class Decoying extends BattleModifier {

    public void SummonLivingEntity(Level Level, Vec3 Position, LivingEntity lv){
        Level.addFreshEntity(lv);
        lv.moveTo(Position);
    }
    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if(!(context.getAttacker() instanceof FakePlayer)) {
            LivingEntity target= context.getLivingTarget();
            int random = RANDOM.nextInt(Math.min(modifier.getLevel(),4));
            if (target!= null) {
                Level level=target.getLevel();
                Chicken chicken=EntityType.CHICKEN.create(level);
                Pig pig=EntityType.PIG.create(level);
                Cow cow=EntityType.COW.create(level);
                Sheep sheep=EntityType.SHEEP.create(level);
                Rabbit rabbit=EntityType.RABBIT.create(level);
                LivingEntity[] livingEntities =new LivingEntity[]{
                        chicken,pig,cow,sheep,rabbit
                };
                this.SummonLivingEntity(level,target.getPosition(0), livingEntities[random]);
            }
        }
    }
}
