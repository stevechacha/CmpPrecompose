package di

import data.local.DatabaseDriverFactory
import data.local.IOSDatabaseDriverFactory
import org.chachadev.cmpprecompose.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { IOSDatabaseDriverFactory().createDriver() }
        single { AppDatabase.invoke(get()) }
    }