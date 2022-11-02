package de.larmic.azure.configuration

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

import de.larmic.azure.service.HelloController
import de.larmic.azure.service.ResponseBodyFactory

object InitKoinDependencyInjection {
    init {
        startKoin {
            modules(module {
                singleOf(::HelloController)
                singleOf(::ResponseBodyFactory)
            })
        }
    }
}