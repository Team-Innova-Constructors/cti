package com.hoshino.cti.Modifier.Contributors;

import com.hoshino.cti.cti;
import com.marth7th.solidarytinker.extend.superclass.ArmorModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class Trauma extends ArmorModifier {
    private static final ResourceLocation HIT = cti.getResource("hit");
    @Override
    public @Nullable Component onRemoved(IToolStackView iToolStackView, Modifier modifier) {
        iToolStackView.getPersistentData().remove(HIT);
        return null;
    }
    @Override
    public void LivingHurtEvent(LivingHurtEvent event) {
        if(event.getEntity() instanceof Player player){
            IToolStackView toolstack= ToolStack.from(player.getMainHandItem());
            ModDataNBT ToolData=toolstack.getPersistentData();
            event.setAmount((float) Math.abs(event.getAmount() - Math.pow(2,ToolData.getInt(HIT))));
        }
    }
}
