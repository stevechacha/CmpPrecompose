package di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import data.local.AndroidDatabaseDriverFactory
import org.chachadev.cmpprecompose.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module{
        single { AndroidDatabaseDriverFactory(get<Context>()).createDriver() }
        single { AppDatabase.invoke(get<SqlDriver>()) }
    }