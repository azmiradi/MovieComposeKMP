package com.azmiradi.moviecomposekmp.data.di

import com.azmiradi.moviecomposekmp.data.di.DBModule.testDb
import com.azmiradi.moviecomposekmp.data.local.SharedDatabase
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

object DBModule {
    val testDb = module {
        factory<SharedDatabase> {
            SharedDatabase()
        }
    }
}

actual fun initKoin(): KoinApplication =
    startKoin {
          modules(
              Modules.servicesAPIs,
              Modules.useCases,
              Modules.repositories,
              Modules.viewModels,
              testDb
        )
    }