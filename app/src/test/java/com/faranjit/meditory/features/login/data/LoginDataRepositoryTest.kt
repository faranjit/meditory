package com.faranjit.meditory.features.login.data

import com.faranjit.meditory.BaseUnitTest
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class LoginDataRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var localDataSource: LoginLocalDataSource

    @InjectMocks
    private lateinit var loginDataRepository: LoginDataRepository

    @Test
    fun `should saveUsername be called`() {
        loginDataRepository.saveUsername("faranjit")
        verify(localDataSource).saveUsername("faranjit")
    }
}