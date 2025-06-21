package com.fadlan.fireporter.model

import javafx.scene.control.Button
import java.awt.Color

data class Theme(val name: String, val button: Button, val color: Color, val darkColor: Color) {
    val colorHex: String = String.format("#%06X", (0xFFFFFF and color.rgb))
    val darkColorHex: String = String.format("#%06X", (0xFFFFFF and darkColor.rgb))
}