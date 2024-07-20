package data.local

import app.cash.sqldelight.db.SqlDriver
import org.chachadev.cmpprecompose.AppDatabase
import app.cash.sqldelight.driver.native.NativeSqliteDriver

class IOSDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "app.db")
    }
}