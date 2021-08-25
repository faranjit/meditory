package com.faranjit.meditory

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.WebSettings
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.faranjit.meditory.base.GlideInstance

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

@BindingAdapter("url")
fun setImageUrl(view: AppCompatImageView, url: String) {
    val glideUrl = GlideUrl(
        url,
        LazyHeaders.Builder()
            .addHeader("User-Agent", WebSettings.getDefaultUserAgent(view.context))
            .build()
    )
    GlideInstance.glide
        .load(glideUrl)
        .placeholder(R.drawable.small)
        .into(view)
}

@BindingAdapter("visibility")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}