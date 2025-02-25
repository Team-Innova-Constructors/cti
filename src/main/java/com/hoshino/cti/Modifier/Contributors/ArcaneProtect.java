package com.hoshino.cti.Modifier.Contributors;

import com.hoshino.cti.util.method.GetModifierLevel;
import com.marth7th.solidarytinker.extend.superclass.ArmorModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class ArcaneProtect extends ArmorModifier {
    @Override
    public boolean havenolevel() {
        return true;
    }
    public ArcaneProtect() {
        MinecraftForge.EVENT_BUS.addListener(this::LivingMagicDamage);
    }

    private void LivingMagicDamage(LivingDamageEvent event) {
        if (event.getSource().isMagic() && event.getEntity() instanceof Player player) {
            if (GetModifierLevel.EquipHasModifierlevel(player, this.getId())) {
                player.invulnerableTime=100;
                event.setAmount(1);
            }
        }
    }
}
