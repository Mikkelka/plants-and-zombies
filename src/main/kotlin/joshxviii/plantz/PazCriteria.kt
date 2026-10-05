package joshxviii.plantz

import joshxviii.plantz.advancement.*
import net.minecraft.advancements.CriterionProgress
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.advancements.triggers.CriterionTrigger

object PazCriteria {

    @JvmField
    val RAID_WAVE_TRIGGER: RaidWaveTrigger = registerTrigger("raid_wave", RaidWaveTrigger())

    @JvmField
    val BOWLING_TRIGGER: WallNutBowlingTrigger = registerTrigger("wallnut_bowling", WallNutBowlingTrigger())

    @JvmField
    val SEND_MAIL = registerCriteria("send_mail", PazSimpleCriterionTrigger(SendMailCriteria.CODEC))

    @JvmField
    val RECEIVE_HERO_MAIL = registerCriteria("receive_hero_mail", PazSimpleCriterionTrigger(SimpleCheckCriteria.CODEC))

    @JvmField
    val RELOCATION = registerCriteria("relocate", PazSimpleCriterionTrigger(RelocatePlantCriteria.CODEC))

    @JvmField
    val GROW_SEEDS = registerCriteria("grow_seeds", PazSimpleCriterionTrigger(GrowSeedsCriteria.CODEC))

    @JvmField
    val GRAVE_BUSTER_BUST = registerCriteria("grave_buster_bust", PazSimpleCriterionTrigger(SimpleCheckCriteria.CODEC))

    @JvmField
    val PLANT_POT_MINECRAFT = registerCriteria("plant_pot_minecart", PazSimpleCriterionTrigger(PlantPotMinecartCriteria.CODEC))

    @JvmField
    val DISCO_HYPNO = registerCriteria("disco_hypno", PazSimpleCriterionTrigger(DiscoHypnoCriteria.CODEC))

    @JvmField
    val WIN_ZOMBIE_RAID = registerCriteria("win_zombie_raid", PazSimpleCriterionTrigger(ZombieRaidCriteria.CODEC))

    @JvmField
    val START_HARDEST_RAID = registerCriteria("start_hardest_raid", PazSimpleCriterionTrigger(SimpleCheckCriteria.CODEC))

    fun <T, E : PazCriterionCondition<T>> registerCriteria(
        name: String,
        trigger: PazSimpleCriterionTrigger<T, E>
    ): PazSimpleCriterionTrigger<T, E> {
        return Registry.register(BuiltInRegistries.TRIGGER_TYPES, pazResource(name), trigger)
    }

    fun <T : CriterionTrigger<*>> registerTrigger(name: String, trigger: T): T {
        return Registry.register(BuiltInRegistries.TRIGGER_TYPES, pazResource(name), trigger)
    }

    fun initialize() {}
}
