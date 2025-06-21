package com.fadlan.fireporter.utils

import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.beans.property.ReadOnlyFloatProperty
import javafx.beans.property.ReadOnlyStringProperty
import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleStringProperty
import javafx.util.Duration

class FxProgressTracker {
    private var currentStep: Float = 0F
    var totalSteps = 1

    private val _progress = SimpleFloatProperty(0F)
    private val _message = SimpleStringProperty("")

    val progressProperty: ReadOnlyFloatProperty = _progress
    val messageProperty: ReadOnlyStringProperty = _message

    fun report(message: String) {
        currentStep++
        val prog = currentStep / totalSteps.toFloat()
        animateProgress(prog)
        sendMessage(message)
    }

    fun sendMessage(message: String? = "") {
        _message.set("[${currentStep.toInt()}/${totalSteps}] $message")
    }

    fun reset() {
        currentStep = 0F
        _message.set("")
        _progress.set(0F)
    }

    fun resetProgress() {
        currentStep = 0F
        _progress.set(0F)
    }

    private fun animateProgress(target: Float) {
        val timeline = Timeline(
            KeyFrame(
                Duration.millis(300.0),
                KeyValue(_progress, target)
            )
        )
        timeline.play()
    }

}