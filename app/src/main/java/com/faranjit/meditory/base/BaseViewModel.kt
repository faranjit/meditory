package com.faranjit.meditory.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Bulent Turkmen on 24.08.2021.
 */
abstract class BaseViewModel : ViewModel() {

    private val loading = MutableLiveData(false)
    val loadingLiveData: LiveData<Boolean>
        get() = loading

    /**
     * ViewModel uzerinden async islem baslatir.
     *
     * @param block CoroutineScope icinde gerceklesen async operasyon.
     */
    fun runAsync(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
//                _spinnerLiveData.value = true

                block.invoke(this)
            } catch (t: Throwable) {
                Log.e(this@BaseViewModel::class.java.canonicalName, t.message, t)
            } finally {
//                _spinnerLiveData.value = false
            }
        }
    }
}