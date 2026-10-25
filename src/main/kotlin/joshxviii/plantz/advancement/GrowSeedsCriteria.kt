package joshxviii.plantz.advancement

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.advancements.predicates.ContextAwarePredicate
import net.minecraft.server.level.ServerPlayer
import java.util.Optional

class GrowSeedsCriteria(
    playerCtx: Optional<ContextAwarePredicate>,
): PazCriterionCondition<Int>(playerCtx) {

    companion object {
        val CODEC: Codec<GrowSeedsCriteria> = RecordCodecBuilder.create { it.group(
            ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(GrowSeedsCriteria::playerCtx)
        ).apply(it, ::GrowSeedsCriteria) }
    }

    override fun matches(player: ServerPlayer, context: Int): Boolean {
        return true
    }
}