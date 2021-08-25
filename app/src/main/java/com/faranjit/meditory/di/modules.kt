package com.faranjit.meditory.di

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.faranjit.meditory.BuildConfig
import com.faranjit.meditory.R
import com.faranjit.meditory.base.Executor
import com.faranjit.meditory.base.RequestExecutor
import com.faranjit.meditory.base.SharedPrefs
import com.faranjit.meditory.features.home.data.HomeApi
import com.faranjit.meditory.features.home.data.HomeDataRepository
import com.faranjit.meditory.features.home.data.datasource.HomeLocalDataSource
import com.faranjit.meditory.features.home.data.datasource.HomeRemoteDataSource
import com.faranjit.meditory.features.home.domain.GetHomeData
import com.faranjit.meditory.features.home.domain.GetUsername
import com.faranjit.meditory.features.home.domain.HomeRepository
import com.faranjit.meditory.features.home.presentation.HomeViewModel
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
import org.koin.android.ext.koin.androidApplication
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
    single<Executor> { RequestExecutor() }
}

val glideModule = module {
    fun provideRequestManager(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    fun provideRequestOptions(): RequestOptions {
        return RequestOptions()
            .placeholder(R.drawable.small)
            .error(R.drawable.small)
    }

    single { provideRequestOptions() }

    single { provideRequestManager(androidApplication(), get()) }
}

val storageModule = module {
    fun provideStorage(context: Context) = SharedPrefs(
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    )

    single { provideStorage(get()) }
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

val homeModule = module {
    fun provideHomeViewModel(getHomeData: GetHomeData, getUsername: GetUsername) =
        HomeViewModel(getHomeData, getUsername)

    fun provideHomeApi(retrofit: Retrofit) = retrofit.create(HomeApi::class.java)

    fun provideHomeRemoteDataSource(shortlyApi: HomeApi, executor: Executor) =
        HomeRemoteDataSource(shortlyApi, executor)

    fun provideHomeLocalDataSource(sharedPrefs: SharedPrefs) = HomeLocalDataSource(sharedPrefs)

    factory<HomeRepository> {
        HomeDataRepository(
            provideHomeRemoteDataSource(provideHomeApi(get()), get()),
            provideHomeLocalDataSource(get())
        )
    }

    factory { GetHomeData(get()) }

    factory { GetUsername(get()) }

    viewModel { provideHomeViewModel(get(), get()) }
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