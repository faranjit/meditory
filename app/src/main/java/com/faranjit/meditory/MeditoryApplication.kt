package com.faranjit.meditory

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.faranjit.meditory.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Bulent Turkmen on 24.08.2021.
 */
class MeditoryApplication : MultiDexApplication() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MeditoryApplication)
            modules(
                networkModule,
                glideModule,
                storageModule,
                loginModule,
                homeModule,
                detailModule
            )
        }
    }
}