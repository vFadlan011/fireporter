package com.fadlan.fireporter.utils

import com.fadlan.fireporter.FireporterApp
import javafx.scene.control.Alert
import javafx.scene.image.Image
import javafx.stage.Stage

class IconizedAlert(
    alertType: AlertType,
    title: String,
    headerText: String,
    contentText: String,
): Alert(alertType) {
    init {
        this.title = title
        this.headerText = headerText
        this.contentText = contentText

        val stage = this.dialogPane.scene.window as Stage
        stage.icons.add(Image(FireporterApp::class.java.getResourceAsStream("fireporter-icon.png")))
    }
}