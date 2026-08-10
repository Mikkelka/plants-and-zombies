package joshxviii.plantz.gui

import joshxviii.plantz.PazClientNetwork.ZombieRaidClientCache
import joshxviii.plantz.PazConfig
import joshxviii.plantz.mixin.client.BossHealthOverlayAccessor
import joshxviii.plantz.pazResource
import net.minecraft.SharedConstants
import net.minecraft.client.DeltaTracker
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.LerpingBossEvent
import net.minecraft.client.renderer.RenderPipelines
import net.minecraft.network.chat.Component
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
        val width = 256
        val height = 32

        val client = Minecraft.getInstance()
        val raidEvent = ZombieRaidClientCache.active.values.firstOrNull() ?: return

        val screenWidth = graphics.guiWidth()
        val x = screenWidth / 2 - (width / 2)
        val y = 0

        graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, x, y, 0f, 0f, width, height, width, height)

        val name = Component.literal("${raidEvent.waveTimer}")
        val textX = screenWidth / 2 - client.font.width(name) / 2
        val textY = y + 18
        graphics.text(client.font, name, textX+1, textY, -0xFFFFFF, false)
        graphics.text(client.font, name, textX-1, textY, -0xFFFFFF, false)
        graphics.text(client.font, name, textX, textY+1, -0xFFFFFF, false)
        graphics.text(client.font, name, textX, textY-1, -0xFFFFFF, false)
        graphics.text(client.font, name, textX, textY, -1, false)

        if (PazConfig.SHOW_DEBUG_INFO) {
            val textX = screenWidth / 2 - client.font.width(raidEvent.status.name) / 2
            graphics.text(client.font, raidEvent.status.name, textX, textY+16, -1)
        }
    }

}