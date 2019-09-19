package com.tem.plate
import androidx.multidex.MultiDexApplication
import com.tem.plate.util.structure.dependecyinjector.applicationModule
import com.tem.plate.util.structure.dependecyinjector.interactorModule
import com.tem.plate.util.structure.dependecyinjector.repositoryModule
import com.tem.plate.util.structure.dependecyinjector.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppTemplateSlimApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppTemplateSlimApplication)
            modules(listOf(interactorModule, repositoryModule, applicationModule, viewModelModule))
        }
    }
}
