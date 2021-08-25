package com.faranjit.meditory.features.home.presentation.model

import junit.framework.TestCase
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class ImageModelTest {

    @Test
    fun `should areItemsTheSame return true`() {
        // Given
        val old = ImageModel(
            "small", "large"
        )

        val new = ImageModel(
            "small", "large"
        )

        // When
        val same = old.areItemsTheSame(old, new)

        // Tnen
        assertTrue(same)
    }

    @Test
    fun `should areItemsTheSame return false`() {
        // Given
        val old = ImageModel(
            "small", "large"
        )

        val new = ImageModel(
            "small2", "large"
        )

        // When
        val same = old.areItemsTheSame(old, new)

        // Tnen
        TestCase.assertFalse(same)
    }

    @Test
    fun `should areContentsTheSame return true`() {
        // Given
        val old = ImageModel(
            "small", "large"
        )

        val new = ImageModel(
            "small", "large"
        )

        // When
        val same = old.areContentsTheSame(old, new)

        // Tnen
        assertTrue(same)
    }

    @Test
    fun `should areContentsTheSame return false`() {
        // Given
        val old = ImageModel(
            "small", "large"
        )

        val new = ImageModel(
            "small2", "large"
        )

        // When
        val same = old.areContentsTheSame(old, new)

        // Tnen
        TestCase.assertFalse(same)
    }
}