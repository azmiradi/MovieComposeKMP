package com.azmiradi.moviecomposekmp.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.azmiradi.moviecomposekmp.Database

actual class SharedDatabase {
    private var database: Database? = null

    internal actual suspend fun invoke(): Database? {
        return createDatabase(NativeSqliteDriver(Database.Schema, "test.db"))
    }

    private fun createDatabase(driver: SqlDriver): Database? {
        if (database == null) {
            database = Database(driver)
        }
        return database
    }
}