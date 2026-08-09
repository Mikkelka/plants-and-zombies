package joshxviii.plantz.ai.goal

import joshxviii.plantz.ai.ZombieState
import joshxviii.plantz.entity.zombie.PazZombie
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.phys.Vec3
import java.util.EnumSet

open class FlyingPathfindingGoal (
    private val entity: PathfinderMob
): Goal() {

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        val target = entity.target ?: return false
        if (target.distanceTo(entity) < 1 || !entity.hasLineOfSight(target)) return false
        return (!entity.onGround() && entity !is PazZombie) || (entity is PazZombie && entity.state == ZombieState.FLYING)
    }

    open fun setEntityDelta(targetPosition: Vec3, distance: Double, speed: Double) {
        entity.deltaMovement = Vec3(
            (targetPosition.x / distance) * speed,
            (targetPosition.y / distance) * speed * 2,
            (targetPosition.z / distance) * speed
        )
    }

    override fun tick() {
        val target = entity.target ?: return
        val targetPosition = target.position().subtract(entity.position())

        val distance = targetPosition.length()

        var flyingSpeed = entity.getAttribute(Attributes.FLYING_SPEED)?.value ?: 0.0

        if (distance <= 0.5) flyingSpeed *= distance
        setEntityDelta(targetPosition, distance, flyingSpeed)

        entity.lookAt(target, 30.0f, 30.0f)
        entity.lookControl.setLookAt(target)
        entity.moveControl.setWantedPosition(target.x, target.y, target.z, 1.5)
    }

}