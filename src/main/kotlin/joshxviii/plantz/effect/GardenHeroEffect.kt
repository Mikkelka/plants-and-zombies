package joshxviii.plantz.effect

import net.minecraft.server.level.ServerLevel
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity

class GardenHeroEffect(
    category: MobEffectCategory,
    color: Int
) : MobEffect(category, color) {

    override fun shouldApplyEffectTickThisTick(remainingDuration: Int, amplification: Int): Boolean {
        return super.shouldApplyEffectTickThisTick(remainingDuration, amplification)
    }

    override fun applyEffectTick(level: ServerLevel, mob: LivingEntity, amplification: Int): Boolean {
        return true
    }


}