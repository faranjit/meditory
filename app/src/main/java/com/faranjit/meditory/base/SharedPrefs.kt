package com.faranjit.meditory.base

import android.content.SharedPreferences

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class SharedPrefs(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val KEY_USER_NAME = "username"
    }

    fun putString(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defValue: String? = null) =
        sharedPreferences.getString(key, defValue)
}