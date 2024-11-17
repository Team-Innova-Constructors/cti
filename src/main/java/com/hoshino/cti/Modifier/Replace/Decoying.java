package com.hoshino.cti.Modifier.Replace;

import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayer;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class Decoying extends BattleModifier {
    public void spawnAnimal(Level world, Entity entity) {
        world.addFreshEntity(entity);
    }
    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if(!(context.getAttacker() instanceof FakePlayer)) {
            LivingEntity player= context.getPlayerAttacker();
            LivingEntity target= context.getLivingTarget();
            int random = RANDOM.nextInt(1, 5);
            int SpawnRandom=RANDOM.nextInt(Math.max(10,100-20*modifier.getLevel()));
            if (target != null&&SpawnRandom<11) {
                Level World=target.getLevel();
                switch (random){
                    case 1:
                        Chicken chicken = EntityType.CHICKEN.create(World);
                        this.spawnAnimal(World,chicken);
                        if (chicken != null) {
                            chicken.moveTo(target.getPosition(0));
                        }
                        break;
                    case 2:
                        Cow cow = EntityType.COW.create(World);
                        this.spawnAnimal(World,cow);
                        if (cow != null) {
                            cow.moveTo(target.getPosition(0));
                        }
                        break;
                    case 3:
                        Sheep sheep=EntityType.SHEEP.create(World);
                        this.spawnAnimal(World,sheep);
                        if (sheep != null) {
                            sheep.moveTo(target.getPosition(0));
                        }
                        break;
                    case 4:
                        Rabbit rabbit=EntityType.RABBIT.create(World);
                        this.spawnAnimal(World,rabbit);
                        if (rabbit != null) {
                            rabbit.moveTo(target.getPosition(0));
                        }
                        break;
                }
            }
        }
    }
}
