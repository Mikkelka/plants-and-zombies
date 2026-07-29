package joshxviii.plantz

import joshxviii.plantz.advancement.*
import net.minecraft.advancements.CriterionProgress
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries

object PazCriteria {

    @JvmField
    val SEND_MAIL = registerCriteria("send_mail", PazSimpleCriterionTrigger(SendMailCriteria.CODEC))

    @JvmField
    val RELOCATION = registerCriteria("relocate", PazSimpleCriterionTrigger(RelocatePlantCriteria.CODEC))

    @JvmField
    val GROW_SEEDS = registerCriteria("grow_seeds", PazSimpleCriterionTrigger(GrowSeedsCriteria.CODEC))

    @JvmField
    val PLANT_POT_MINECRAFT = registerCriteria("plant_pot_minecart", PazSimpleCriterionTrigger(PlantPotMinecartCriteria.CODEC))

    @JvmField
    val DISCO_HYPNO = registerCriteria("disco_hypno", PazSimpleCriterionTrigger(DiscoHypnoCriteria.CODEC))

    @JvmField
    val WIN_ZOMBIE_RAID = registerCriteria("win_zombie_raid", PazSimpleCriterionTrigger(ZombieRaidCriteria.CODEC))

    fun <T, E : PazCriterionCondition<T>> registerCriteria(
        name: String,
        trigger: PazSimpleCriterionTrigger<T, E>
    ): PazSimpleCriterionTrigger<T, E> {
        return Registry.register(BuiltInRegistries.TRIGGER_TYPES, pazResource(name), trigger)
    }

    fun initialize() {}
}