/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toMap
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.OrderDirection
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.facetGrid
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.letsplot.settings.Symbol
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.letsPlot.intern.toSpec
import kotlin.test.Test
import kotlin.test.assertEquals

class ToLetsPlotTest {

    @Test
    fun testSimple() {
        val dataset = dataFrameOf(
            "origin" to listOf<String>(),
            "mpg" to listOf<Double>()
        )
        val plot = dataset.plot {
            x(column<String>("origin"))
            points {
                y(column<Double>("mpg")) {
                    scale = continuous(1.0..5.0)
                }
                symbol = Symbol.CIRCLE_FILLED
                fillColor = Color.RED
            }
        }

        assertEquals(
            mapOf(
                "mapping" to mapOf<String, String>(),
                "data" to dataset.toMap(),
                "kind" to "plot",
                "scales" to listOf(
                    mapOf(
                        "aesthetic" to "x",
                        "limits" to listOf(null, null)
                    ),
                    mapOf(
                        "aesthetic" to "y",
                        "limits" to listOf(1.0, 5.0)
                    )
                ),
                "layers" to listOf(
                    mapOf(
                        "mapping" to mapOf(
                            "x" to "origin",
                            "y" to "mpg"
                        ),
                        "sampling" to "none",
                        "stat" to "identity",
                        "shape" to 21.0,
                        "position" to "identity",
                        "inherit_aes" to false,
                        "geom" to "point",
                        "fill" to "#ee6666"
                    )
                ),
            ),
            plot.toLetsPlot().toSpec()
        )
    }

    @Test
    fun testComplex() {
        val dataset = dataFrameOf(
            "svalue" to listOf<String>(),
            "time" to listOf<Double>(),
            "clM" to listOf<Int>(),
            "clX" to listOf<String>(),
        )
        val clM = column<Int>("clM")
        val plot = dataset.plot {
            x(column<Double>("time")) {
                scale = continuous(limits = -12.0..4.4)
            }
            y(column<String>("svalue")) {
                scale = categorical(categories = listOf("A", "B", "C"))
            }

            bars {
                fillColor(clM) {
                    scale =
                        categorical(
                            range = listOf(Color.RED, Color.hex("#bb11aa"))
                        )
                }
                width = 0.5
                alpha = 0.8

                position = Position.Stack
            }
            line {
                width = 2.2
                type = LineType.DOTTED
                position = Position.Identity
            }

            facetGrid(
                x = clM,
                y = column<String>("clX"),
                yOrder = OrderDirection.DESCENDING
            )
        }
        assertEquals(
            mapOf(
                "mapping" to mapOf<String, String>(),
                "data" to dataset.toMap(),
                "kind" to "plot",
                "scales" to listOf(
                    mapOf(
                        "aesthetic" to "x",
                        "limits" to listOf(-12.0, 4.4)
                    ),
                    mapOf(
                        "aesthetic" to "y",
                        "limits" to listOf("A", "B", "C"),
                        "discrete" to true
                    ),
                    mapOf(
                        "aesthetic" to "fill",
                        "values" to listOf("#ee6666", "#bb11aa")
                    ),
                    mapOf(
                        "aesthetic" to "x",
                        "limits" to listOf(-12.0, 4.4)
                    ),
                    mapOf(
                        "aesthetic" to "y",
                        "limits" to listOf("A", "B", "C"),
                        "discrete" to true
                    ),
                ),
                "layers" to listOf(
                    mapOf(
                        "mapping" to mapOf(
                            "x" to "time",
                            "y" to "svalue",
                            "fill" to "clM"
                        ),
                        "sampling" to "none",
                        "stat" to "identity",
                        "alpha" to 0.8,
                        "width" to 0.5,
                        "position" to "stack",
                        "inherit_aes" to false,
                        "geom" to "bar",
                    ),
                    mapOf(
                        "mapping" to mapOf(
                            "x" to "time",
                            "y" to "svalue",
                        ),
                        "sampling" to "none",
                        "stat" to "identity",
                        "size" to 2.2,
                        "linetype" to "dotted",
                        "position" to "identity",
                        "inherit_aes" to false,
                        "geom" to "line",
                    ),
                ),
                "facet" to mapOf<String, Any>(
                    "name" to "grid",
                    "x" to "clM",
                    "y" to "clX",
                    "x_order" to 1.0,
                    "y_order" to -1.0
                ),
            ),
            plot.toLetsPlot().toSpec()
        )
    }
}
