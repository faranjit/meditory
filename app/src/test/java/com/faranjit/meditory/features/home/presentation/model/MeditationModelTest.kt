package com.faranjit.meditory.features.home.presentation.model

import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class MeditationModelTest {

    @Test
    fun `should areItemsTheSame return true`() {
        // Given
        val old = MeditationModel(
            "name", "subtitle", ImageModel("", ""), "date", "text"
        )

        val new = MeditationModel(
            "name", "subtitle", ImageModel("", ""), "date", "text"
        )

        // When
        val same = old.areItemsTheSame(old, new)

        // Tnen
        assertTrue(same)
    }

    @Test
    fun `should areItemsTheSame return false`() {
        // Given
        val old = MeditationModel(
            "name", "subtitle", ImageModel("", ""), "date", "text"
        )

        val new = MeditationModel(
            "name2", "subtitle", ImageModel("", ""), "date", "text"
        )

        // When
        val same = old.areItemsTheSame(old, new)

        // Tnen
        assertFalse(same)
    }

    @Test
    fun `should areContentsTheSame return true`() {
        // Given
        val old = MeditationModel(
            "name", "subtitle", ImageModel("", ""), "date", "text"
        )

        val new = MeditationModel(
            "name", "subtitle", ImageModel("", ""), "date", "text"
        )

        // When
        val same = old.areContentsTheSame(old, new)

        // Tnen
        assertTrue(same)
    }

    @Test
    fun `should areContentsTheSame return false`() {
        // Given
        val old = MeditationModel(
            "name", "subtitle", ImageModel("", ""), "date", "text"
        )

        val new = MeditationModel(
            "name2", "subtitle", ImageModel("", ""), "date", "text"
        )

        // When
        val same = old.areContentsTheSame(old, new)

        // Tnen
        assertFalse(same)
    }
}