package joshxviii.plantz.gui

import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.network.chat.MutableComponent
import net.minecraft.util.ARGB

fun GuiGraphicsExtractor.outlineText(font: Font, text: MutableComponent, x: Int = 0, y: Int = 0, color: Int = -1, outlineColor: Int = -0xFFFFFF) {
    text(font, text, x+1, y, outlineColor, false)
    text(font, text, x-1, y, outlineColor, false)
    text(font, text, x, y+1, outlineColor, false)
    text(font, text, x, y-1, outlineColor, false)
    text(font, text, x, y, color, false)
}
