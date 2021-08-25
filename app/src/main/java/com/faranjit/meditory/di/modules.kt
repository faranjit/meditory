package com.faranjit.meditory.di

import android.content.Context
import com.faranjit.meditory.BuildConfig
import com.faranjit.meditory.base.SharedPrefs
import com.faranjit.meditory.features.login.data.LoginDataRepository
import com.faranjit.meditory.features.login.data.LoginLocalDataSource
import com.faranjit.meditory.features.login.domain.LoginRepository
import com.faranjit.meditory.features.login.domain.SaveUsername
import com.faranjit.meditory.features.login.presentation.LoginViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by Bulent Turkmen on 24.08.2021.
 */
private const val BASE_URL = "https://media.meditopia.com/"

val networkModule = module {
    single { createOkHttp() }
    single { createJson() }
    single { createRetrofit(get(), get()) }
}

val loginModule = module {
    fun provideLoginViewModel(saveUsername: SaveUsername) = LoginViewModel(saveUsername)

    fun provideLoginLocalDataSource(sharedPrefs: SharedPrefs) = LoginLocalDataSource(sharedPrefs)

    factory<LoginRepository> {
        LoginDataRepository(
            provideLoginLocalDataSource(get())
        )
    }

    factory {
        SaveUsername(get())
    }

    viewModel { provideLoginViewModel(get()) }
}

val storageModule = module {
    fun provideStorage(context: Context) = SharedPrefs(
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    )

    single { provideStorage(get()) }
}

private fun createOkHttp() = OkHttpClient.Builder()
    .connectTimeout(120, TimeUnit.SECONDS)
    .readTimeout(120, TimeUnit.SECONDS)
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    })
    .build()

private fun createRetrofit(okHttpClient: OkHttpClient, json: Json) = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

private fun createJson() = Json {
    ignoreUnknownKeys = true
}