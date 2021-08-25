package com.faranjit.meditory.features.login.data

import com.faranjit.meditory.BaseUnitTest
import com.faranjit.meditory.base.SharedPrefs
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class LoginLocalDataSourceTest : BaseUnitTest() {

    @Mock
    private lateinit var sharedPrefs: SharedPrefs

    @InjectMocks
    private lateinit var localDataSource: LoginLocalDataSource

    @Test
    fun `should putString be called`() {
        localDataSource.saveUsername("asd")
        verify(sharedPrefs).putString(SharedPrefs.KEY_USER_NAME, "asd")
    }
}