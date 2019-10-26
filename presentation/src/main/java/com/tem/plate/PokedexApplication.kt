package com.tem.plate

import android.app.Application
import com.tem.plate.util.dependecyinjector.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                listOf(
                    interactorModule,
                    repositoryModule,
                    applicationModule,
                    viewModelModule,
                    requestModule
                )
            )
        }
    }
}
