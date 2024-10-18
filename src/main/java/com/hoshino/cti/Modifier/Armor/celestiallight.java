package com.hoshino.cti.Modifier.Armor;

import com.marth7th.solidarytinker.extend.superclass.ArmorModifier;
import com.marth7th.solidarytinker.util.method.modifierlevel;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class celestiallight extends ArmorModifier {
    @Override
    public boolean havenolevel() {
        return true;
    }

    @Override
    public void LivingHurtEvent(LivingHurtEvent event) {
        if(event.getEntity()!=null&& modifierlevel.EquipHasModifierlevel(event.getEntity(),this.getId())){
            if(event.getEntity().isDeadOrDying()){
                event.setCanceled(true);
            }
        }
    }
}
