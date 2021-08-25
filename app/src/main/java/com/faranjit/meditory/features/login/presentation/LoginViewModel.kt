package com.faranjit.meditory.features.login.presentation

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faranjit.meditory.base.BaseViewModel
import com.faranjit.meditory.features.login.domain.SaveUsername
import com.faranjit.meditory.getOrDefault
import java.util.regex.Pattern

/**
 * Created by Bulent Turkmen on 24.08.2021.
 */
class LoginViewModel(
    private val saveUsername: SaveUsername
) : BaseViewModel() {

    companion object {
        private const val MIN_USER_NAME_LENGTH = 2
        private const val PATTERN = "((?=.*\\d)(?=.*[A-Z]).{6,20})"
    }

    val usernameObservable = ObservableField<String>()
    val passwordObservable = ObservableField<String>()

    val signinSuccess = MutableLiveData<Boolean>()
    val signinSuccessLiveData: LiveData<Boolean>
        get() = signinSuccess

    private fun isUserNameValid(): Boolean = usernameObservable.getOrDefault("").length > 2

    private fun isPasswordValid(): Boolean {
        val password = passwordObservable.getOrDefault("")
        val pattern = Pattern.compile(PATTERN)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

    /**
     * Username ve password'u kontrol eder.
     * Gecerli ise sign in islemini gerceklestirir
     */
    fun signIn() {
        val valid = isUserNameValid() && isPasswordValid()
        if (valid) {
            saveUsername.execute(
                SaveUsername.Params(usernameObservable.getOrDefault(""))
            )
        }
        signinSuccess.value = valid
    }
}