package com.azmiradi.moviecomposekmp.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.sqljs.initSqlDriver
import com.azmiradi.moviecomposekmp.Database
import com.azmiradi.moviecomposekmp.Database.Companion.Schema
import kotlinx.coroutines.await

 actual class SharedDatabase {

    private var database: Database? = null

    internal actual suspend fun invoke(): Database? {
        return createDatabase(initSqlDriver(Schema).await())
    }

    private fun createDatabase(sqliteDriver: SqlDriver): Database? {
        if (database == null) {
            database = Database(sqliteDriver)
        }
        return database
    }

}
