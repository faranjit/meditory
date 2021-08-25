package com.faranjit.meditory

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
inline fun <T> LifecycleOwner?.observeLiveData(
    liveData: LiveData<T>,
    crossinline observe: (T) -> Unit
) = this?.run {
    Observer<T> {
        observe(it)
    }.also {
        liveData.observe(this, it)
    }
}

fun <T> ObservableField<T>.getOrDefault(defValue: T) = this.get() ?: defValue

fun View.hideSoftInput() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}