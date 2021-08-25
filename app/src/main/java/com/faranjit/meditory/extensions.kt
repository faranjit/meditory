package com.faranjit.meditory

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.WebSettings
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.faranjit.meditory.base.GlideInstance
import java.text.SimpleDateFormat
import java.util.*

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

@BindingAdapter("imgRes")
fun setIntResource(view: AppCompatImageView, resId: Int) {
    view.setImageResource(resId)
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

@BindingAdapter("bgUrl")
fun setBackgroundUrl(view: View, url: String) {
    val glideUrl = GlideUrl(
        url,
        LazyHeaders.Builder()
            .addHeader("User-Agent", WebSettings.getDefaultUserAgent(view.context))
            .build()
    )

    GlideInstance.glide
        .load(glideUrl)
        .into(object : CustomViewTarget<View, Drawable>(view) {

            override fun onLoadFailed(errorDrawable: Drawable?) {
                Log.e(this::class.java.canonicalName, "glide error")
            }

            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                view.background = resource
            }

            override fun onResourceCleared(placeholder: Drawable?) {
                view.setBackgroundResource(R.drawable.bg_purple_rounded)
            }

        })
}

@BindingAdapter("visibility")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("date")
fun setDateText(view: AppCompatTextView, text: String) {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = text.toLong()

    val simpleDateFormat = SimpleDateFormat("MM/dd/yyy, EEE", Locale.getDefault())
    view.text = simpleDateFormat.format(calendar.time)
}