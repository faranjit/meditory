package com.faranjit.meditory.base

import androidx.lifecycle.Observer
import com.faranjit.meditory.BaseUnitTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.verify

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class BaseViewModelTest : BaseUnitTest() {

    private lateinit var viewModel: BaseViewModel

    @Mock
    lateinit var spinnerObserver: Observer<Boolean>

    @Before
    fun setup() {
        viewModel = object : BaseViewModel() {

        }
    }

    @Test
    fun shouldDisplaySpinnerDuringRequest() = runBlocking {
        viewModel.loadingLiveData.observeForever(spinnerObserver)

        viewModel.runAsync {
            // dummy lines
            val a = 2
        }

        verify(spinnerObserver).onChanged(true)
        verify(spinnerObserver, atLeastOnce()).onChanged(false)

        viewModel.loadingLiveData.removeObserver(spinnerObserver)
    }
}