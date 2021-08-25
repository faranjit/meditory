package com.faranjit.meditory.features.login.presentation

import com.faranjit.meditory.BaseUnitTest
import com.faranjit.meditory.features.login.domain.SaveUsername
import com.faranjit.meditory.getOrAwaitValue
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class LoginViewModelTest : BaseUnitTest() {

    @Mock
    private lateinit var saveUsername: SaveUsername

    @InjectMocks
    private lateinit var viewModel: LoginViewModel

    @Test
    fun `should sign in be successful`() {
        // Given
        viewModel.usernameObservable.set("faranjit")
        viewModel.passwordObservable.set("Abc123a")

        // When
        viewModel.signIn()

        // Then
        val success = viewModel.signinSuccessLiveData.getOrAwaitValue()
        assertTrue(success)
        verify(saveUsername).execute(SaveUsername.Params("faranjit"))
    }

    @Test
    fun `should sign in be failed for missing character size`() {
        // Given
        viewModel.usernameObservable.set("faranjit")
        viewModel.passwordObservable.set("abc")

        // When
        viewModel.signIn()

        // Then
        val success = viewModel.signinSuccessLiveData.getOrAwaitValue()
        assertFalse(success)
    }

    @Test
    fun `should sign in be failed for missing upper case`() {
        // Given
        viewModel.usernameObservable.set("faranjit")
        viewModel.passwordObservable.set("abc123")

        // When
        viewModel.signIn()

        // Then
        val success = viewModel.signinSuccessLiveData.getOrAwaitValue()
        assertFalse(success)
    }
}