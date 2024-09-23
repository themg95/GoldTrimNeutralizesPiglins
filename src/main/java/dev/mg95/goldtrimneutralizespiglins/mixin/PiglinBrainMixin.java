package dev.mg95.goldtrimneutralizespiglins.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.ItemStack;
import net.minecraft.item.trim.ArmorTrimMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {
    @Inject(at = @At("TAIL"), method = "wearsGoldArmor", cancellable = true)
    private static void wearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        Iterable<ItemStack> armorItems = entity.getAllArmorItems();

        for (var armor : armorItems) {
            var trim = armor.getComponents().get(DataComponentTypes.TRIM);
            if (trim == null) continue;
            if (trim.getMaterial().getIdAsString().equals("minecraft:gold")) {
                cir.setReturnValue(true);
                return;
            }
        }
    }
}
