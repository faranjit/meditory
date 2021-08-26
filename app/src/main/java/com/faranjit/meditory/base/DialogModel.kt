package com.faranjit.meditory.base

/**
 * Created by Bulent Turkmen on 26.08.2021.
 */
data class DialogModel(
    val title: String,
    val message: String,
    val positiveButton: DialogButton,
    val negativeButton: DialogButton? = null
)

data class DialogButton(
    val text: String,
    val task: (() -> Unit)? = null
)