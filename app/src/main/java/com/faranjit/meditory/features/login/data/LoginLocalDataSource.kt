package com.faranjit.meditory.features.login.data

import com.faranjit.meditory.base.SharedPrefs

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class LoginLocalDataSource(
    private val sharedPrefs: SharedPrefs
) {

    /**
     * Username'i local storage'a kaydeder.
     */
    fun saveUsername(username: String) = sharedPrefs.putString(SharedPrefs.KEY_USER_NAME, username)
}