package com.faranjit.meditory.features.home.data.datasource

import com.faranjit.meditory.base.SharedPrefs

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class HomeLocalDataSource(
    private val prefs: SharedPrefs
) {

    /**
     * Login'de kaydedilen username'i getirir.
     */
    fun getUsername() = prefs.getString(SharedPrefs.KEY_USER_NAME, "")
}