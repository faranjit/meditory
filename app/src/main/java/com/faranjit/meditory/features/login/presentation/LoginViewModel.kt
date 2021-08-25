package com.faranjit.meditory.features.login.presentation

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
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

    private var isPasswordVisible = false

    private val signinSuccess = MutableLiveData<Boolean>()
    val signinSuccessLiveData: LiveData<Boolean>
        get() = signinSuccess

    private val passwordVisibility = MutableLiveData<TransformationMethod>()
    val passwordVisibilityLiveData: LiveData<TransformationMethod>
        get() = passwordVisibility

    init {
        passwordVisibility.value = PasswordTransformationMethod.getInstance()
    }

    private fun isUserNameValid(): Boolean =
        usernameObservable.getOrDefault("").length > MIN_USER_NAME_LENGTH

    private fun isPasswordValid(): Boolean {
        val password = passwordObservable.getOrDefault("")
        val pattern = Pattern.compile(PATTERN)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

    /**
     * Password gorunulurlugunu degistirir.
     * Visibiltiy acik ise kapali yapar, kapali ise acik yapar.
     */
    fun showOrHidePassword() {
        isPasswordVisible = !isPasswordVisible
        passwordVisibility.value = if (isPasswordVisible) {
            PasswordTransformationMethod.getInstance()
        } else {
            HideReturnsTransformationMethod.getInstance()
        }
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