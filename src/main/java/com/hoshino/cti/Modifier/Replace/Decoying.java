package com.hoshino.cti.Modifier.Replace;

import com.marth7th.solidarytinker.extend.superclass.BattleModifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.npc.Villager;
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
            LivingEntity target= context.getLivingTarget();
            int random = RANDOM.nextInt(1, modifier.getLevel()+1);
            if (target != null) {
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
                        Pig pig = EntityType.PIG.create(World);
                        this.spawnAnimal(World,pig);
                        if (pig != null) {
                            pig.moveTo(target.getPosition(0));
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
                        Cow cow=EntityType.COW.create(World);
                        this.spawnAnimal(World,cow);
                        if (cow != null) {
                            cow.moveTo(target.getPosition(0));
                        }
                        break;
                    case 5:
                        Rabbit rabbit=EntityType.RABBIT.create(World);
                        this.spawnAnimal(World,rabbit);
                        if (rabbit != null) {
                            rabbit.moveTo(target.getPosition(0));
                        }
                        break;
                    case 6:
                        Villager villager=EntityType.VILLAGER.create(World);
                        this.spawnAnimal(World,villager);
                        if (villager != null) {
                            villager.moveTo(target.getPosition(0));
                        }
                        break;
                }
            }
        }
    }
}
