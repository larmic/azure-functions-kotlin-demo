package de.larmic.azure.configuration

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

import de.larmic.azure.service.HelloServiceDelegate
import de.larmic.azure.service.HelloService

// Start koin dependency injection using a singleton object
// add private val initDI = InitKoinDependencyInjection to azure function class
object InitKoinDependencyInjection {
    init {
        startKoin {
            modules(module {
                singleOf(::HelloServiceDelegate)
                singleOf(::HelloService)
            })
        }
    }
}