package com.faranjit.meditory.features.home.presentation.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class StoryModelTest {

    @Test
    fun `should areItemsTheSame return true`() {
        // Given
        val old = StoryModel(
            "name", "category", ImageModel("", ""), "date", "text"
        )

        val new = StoryModel(
            "name", "category", ImageModel("", ""), "date", "text"
        )

        // When
        val same = old.areItemsTheSame(old, new)

        // Tnen
        assertTrue(same)
    }

    @Test
    fun `should areItemsTheSame return false`() {
        // Given
        val old = StoryModel(
            "name", "category", ImageModel("", ""), "date", "text"
        )

        val new = StoryModel(
            "name2", "category", ImageModel("", ""), "date", "text"
        )

        // When
        val same = old.areItemsTheSame(old, new)

        // Tnen
        assertFalse(same)
    }

    @Test
    fun `should areContentsTheSame return true`() {
        // Given
        val old = StoryModel(
            "name", "category", ImageModel("", ""), "date", "text"
        )

        val new = StoryModel(
            "name", "category", ImageModel("", ""), "date", "text"
        )

        // When
        val same = old.areContentsTheSame(old, new)

        // Tnen
        assertTrue(same)
    }

    @Test
    fun `should areContentsTheSame return false`() {
        // Given
        val old = StoryModel(
            "name", "category", ImageModel("", ""), "date", "text"
        )

        val new = StoryModel(
            "name2", "category", ImageModel("", ""), "date", "text"
        )

        // When
        val same = old.areContentsTheSame(old, new)

        // Tnen
        assertFalse(same)
    }
}