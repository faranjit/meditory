package com.faranjit.meditory.base

import com.bumptech.glide.RequestManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
object GlideInstance : KoinComponent {
    val glide: RequestManager by inject()
}