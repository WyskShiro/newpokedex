package com.tem.plate.util.dependecyinjector

import com.tem.domain.util.StringsProvider
import com.tem.plate.util.ErrorHandler
import com.tem.plate.util.resources.AndroidStringProvider
import org.koin.android.logger.AndroidLogger
import org.koin.dsl.bind
import org.koin.dsl.module
import java.util.logging.Logger

val applicationModule = module {
    single<StringsProvider> { AndroidStringProvider(get()) }
    single { AndroidLogger(get()) } bind Logger::class
    single { ErrorHandler(get(), get()) }
}