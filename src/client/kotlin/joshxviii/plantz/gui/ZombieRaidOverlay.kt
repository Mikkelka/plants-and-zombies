package joshxviii.plantz.gui

import joshxviii.plantz.mixin.client.BossHealthOverlayAccessor
import joshxviii.plantz.pazResource
import net.minecraft.client.DeltaTracker
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.LerpingBossEvent
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.resources.Identifier

object ZombieRaidOverlay {

    @JvmStatic
    fun isZombieRaid(event: LerpingBossEvent): Boolean {
        return event.name.toString().contains("zombie")
    }

    val BACKGROUND: Identifier = pazResource("textures/gui/raid/background.png")

    fun extractRenderState(
        graphics: GuiGraphicsExtractor,
        deltaTracker: DeltaTracker
    ) {
        val width = 182
        val height = 32

        val client = Minecraft.getInstance()
        val raidEvent = (client.gui.bossOverlay as BossHealthOverlayAccessor).`plantz$getAllEvents`().values.firstOrNull { isZombieRaid(it) }?: return


        val screenWidth = graphics.guiWidth()
        val x = screenWidth / 2 - 91
        val y = 0

        graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, x, y, 0f, 0f, width, height, width, height)

        val progressWidth = (182 * (raidEvent.progress ?:1f)).toInt()

        val name = raidEvent.name
        val textX = screenWidth / 2 - client.font.width(name) / 2
        graphics.text(client.font, name, textX, y, -1)
    }

}