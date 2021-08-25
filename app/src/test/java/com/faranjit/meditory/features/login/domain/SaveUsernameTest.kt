package com.faranjit.meditory.features.login.domain

import com.faranjit.meditory.BaseUnitTest
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class SaveUsernameTest : BaseUnitTest() {

    @Mock
    private lateinit var repository: LoginRepository

    @InjectMocks
    private lateinit var saveUsername: SaveUsername

    @Test
    fun `should repository be called`() {
        // When
        val params = SaveUsername.Params("asd")

        // Given
        saveUsername.execute(params)

        // Then
        verify(repository).saveUsername("asd")
    }
}