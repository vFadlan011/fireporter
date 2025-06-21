package com.fadlan.fireporter.utils

import net.sf.jasperreports.charts.JRChart
import net.sf.jasperreports.charts.JRChartCustomizer
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import java.awt.BasicStroke
import java.awt.Color


class ThemeChartCustomizer: JRChartCustomizer {
    override fun customize(chart: JFreeChart?, jasperChart: JRChart) {
        val propertiesMap = jasperChart.propertiesMap
        if (propertiesMap != null) {
            val paramValue = propertiesMap.getProperty("property.theme_dark_color")
            if (paramValue != null && paramValue != "") {
                try {
                    val dynamicColor = Color.decode(paramValue)

                    val plot = chart?.plot as? XYPlot
                    if (plot != null) {
                        val renderer = plot.renderer as? XYLineAndShapeRenderer
                        if (renderer != null) {
                            renderer.setSeriesPaint(0, dynamicColor)

                            renderer.setSeriesStroke(0, BasicStroke(1.5f))

                            renderer.setSeriesShapesVisible(0, false)
                            renderer.setSeriesShapesFilled(0, true)
                        }
                    }
                } catch (e: Exception) {
                    println("Error applying theme color: ${e.message}")
                }
            }
        }
    }
}