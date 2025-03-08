package com.hoshino.cti.Modifier.Contributors;

import com.hoshino.cti.util.method.GetModifierLevel;
import com.marth7th.solidarytinker.extend.superclass.ArmorModifier;
import com.marth7th.solidarytinker.register.TinkerCuriosModifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.ArrayList;
import java.util.List;

public class ArcaneProtect extends ArmorModifier {
    @Override
    public boolean havenolevel() {
        return true;
    }
    public int fakeInvurabletime=0;
    public void setfakeInvurabletime(int time){
        this.fakeInvurabletime=time;
    }

    public ArcaneProtect() {
        MinecraftForge.EVENT_BUS.addListener(this::LivingMagicDamage);
    }

    private void LivingMagicDamage(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player && GetModifierLevel.EquipHasModifierlevel(player, this.getId())) {
            if (event.getSource().isMagic()) {
                event.setAmount(1);
            }
            List<ItemStack>itemStacks=player.getInventory().armor;
            List<IToolStackView>views=new ArrayList<>();
            for(ItemStack tool:itemStacks){
                if(ModifierUtil.getModifierLevel(tool,this.getId())>0){
                    IToolStackView correct =ToolStack.from(tool);
                    views.add(correct);
                    break;
                }
            }
            Modifier modifier= views.get(0).getModifier(this).getModifier();
            if(modifier instanceof ArcaneProtect arc){
                if(arc.fakeInvurabletime==60){
                    arc.setfakeInvurabletime(0);
                    player.setInvulnerable(true);
                }
            }
        }
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity entity, int index, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if(isCorrectSlot){
            if(modifier.getModifier() instanceof ArcaneProtect arcaneProtect){
                if(arcaneProtect.fakeInvurabletime<60){
                    arcaneProtect.setfakeInvurabletime(arcaneProtect.fakeInvurabletime+1);
                }
                else if(arcaneProtect.fakeInvurabletime==60&&entity instanceof Player player&&!GetModifierLevel.CurioHasModifierlevel(player, TinkerCuriosModifier.NATIVED_STATIC_MODIFIER.getId())){
                    entity.setInvulnerable(false);
                }
            }
        }
    }
}
