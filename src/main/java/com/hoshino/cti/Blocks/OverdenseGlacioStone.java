package com.hoshino.cti.Blocks;

import com.c2h6s.etshtinker.Entities.plasmaexplosionentity;
import com.c2h6s.etshtinker.init.etshtinkerEntity;
import com.c2h6s.etshtinker.init.etshtinkerParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class OverdenseGlacioStone extends Block {

    public OverdenseGlacioStone(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(300)==1) {
            BlockPos blockpos = new BlockPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
            BlockState blockstate = pLevel.getBlockState(blockpos);
            int a = 0;
            while (a <= 20 && (blockstate.is(Blocks.AIR) || blockstate.is(Blocks.CAVE_AIR) || blockstate.is(Blocks.VOID_AIR) || blockstate.is(Blocks.WATER))) {
                blockpos = new BlockPos(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                blockstate = pLevel.getBlockState(blockpos);
                a++;
            }
            if (a < 20) {
                plasmaexplosionentity entity = new plasmaexplosionentity(etshtinkerEntity.plasmaexplosionentity.get(), pLevel);
                entity.particle = etshtinkerParticleType.plasmaexplosionpurple.get();
                entity.special = "entropic";
                entity.scale = 0.8f;
                entity.damage = 75;
                entity.rayVec3 = new Vec3(0, 24 + 12 * pRandom.nextFloat(), 0);
                entity.setPos(blockpos.getX(), blockpos.getY(), blockpos.getZ());
                pLevel.addFreshEntity(entity);
                pLevel.playLocalSound(blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.AMBIENT, 2, 2, true);
                pLevel.playLocalSound(blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.FIREWORK_ROCKET_BLAST_FAR, SoundSource.AMBIENT, 2, 2, true);
                pLevel.playLocalSound(blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.FIREWORK_ROCKET_TWINKLE, SoundSource.AMBIENT, 2, 2, true);
            }
        }
    }
}
