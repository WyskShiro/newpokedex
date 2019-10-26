package com.tem.plate.util.dependecyinjector

import com.tem.data.api.ApiClient
import com.tem.domain.util.StringsProvider
import com.tem.plate.util.ErrorHandler
import com.tem.plate.util.resources.AndroidStringProvider
import com.tem.plate.util.resources.SchedulerProvider
import org.koin.android.logger.AndroidLogger
import org.koin.dsl.bind
import org.koin.dsl.module
import java.util.logging.Logger

val applicationModule = module {
    factory<StringsProvider> { AndroidStringProvider(get()) }
    factory { SchedulerProvider() }
    factory { AndroidLogger(get()) } bind Logger::class
    factory { ErrorHandler() }
    factory { ApiClient(get()) }
}