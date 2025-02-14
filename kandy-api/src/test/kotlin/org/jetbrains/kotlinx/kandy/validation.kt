/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy

import org.jetbrains.kotlinx.kandy.dsl.internal.checkInRange
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ValidationTests {

    @Test
    fun `test value in range`() {
        val value = 5
        val range = 1..10
        val aes = Aes("Test")

        aes.checkInRange(value, range)
    }

    @Test
    fun `test value out of range`() {
        val value = 15
        val range = 1..10
        val aes = Aes("Test")

        val exception = assertFailsWith<IllegalArgumentException> {
            aes.checkInRange(value, range)
        }
        assertEquals(
            "Value `$value` of `${aes.name}` is outside the range [${range.first}, ${range.last}].",
            exception.message
        )
    }
    // TODO(https://github.com/Kotlin/kandy/issues/422)
    /*
    class LayerBuilderMockk(
        parentBuilder: MultiLayerPlotBuilder,
        override val requiredAes: Set<Aes>,
        override var inheritMappings: Boolean
    ): LayerBuilderImpl(parentBuilder) {
        override val geom: Geom = mockk()
    }
    @Test
    fun `test required Aes from mapping and settings in layer`() {
        val requiredAes = setOf(Aes("A"), Aes("B"))
        val plotBuilder = mockk<MultiLayerPlotBuilder>()

        val layerBuilder = LayerBuilderMockk(plotBuilder, requiredAes, false)
        every { layerBuilder.requiredAes } returns requiredAes
        every { layerBuilder.bindingCollector.mappings.keys } returns mutableSetOf(Aes("A"))
        every { layerBuilder.bindingCollector.settings.keys } returns mutableSetOf(Aes("B"))

        layerBuilder.checkRequiredAes()
    }

    @Test
    fun `test required Aes from mapping and settings in layerBuilder and plotBuilder`() {
        val requiredAes = setOf(Aes("A"), Aes("B"), Aes("C"), Aes("D"))
        val plotBuilder = mockk<MultiLayerPlotBuilder>()
        every { plotBuilder.bindingCollector.mappings.keys } returns mutableSetOf(Aes("C"))
        every { plotBuilder.bindingCollector.settings.keys } returns mutableSetOf(Aes("D"))
        val layerBuilder = LayerBuilderMockk(plotBuilder, requiredAes, true)
        every { layerBuilder.requiredAes } returns requiredAes
        every { layerBuilder.bindingCollector.mappings.keys } returns mutableSetOf(Aes("A"))
        every { layerBuilder.bindingCollector.settings.keys } returns mutableSetOf(Aes("B"))

        layerBuilder.checkRequiredAes()
    }

    @Test
    fun `test required Aes not assigned`() {
        val requiredAes = setOf(Aes("A"), Aes("B"), Aes("C"))
        val plotBuilder = mockk<MultiLayerPlotBuilder>()
        val layerBuilder = LayerBuilderMockk(plotBuilder, requiredAes, false)
        //layerBuilder.inheritMappings = false
        every { layerBuilder.bindingCollector.mappings.keys } returns mutableSetOf(Aes("A"))
        every { layerBuilder.bindingCollector.settings.keys } returns mutableSetOf(Aes("B"))

        val exception = assertFailsWith<IllegalArgumentException> {
            layerBuilder.checkRequiredAes()
        }

        assertEquals("`C` is not assigned.", exception.message)
    }
*/
}
