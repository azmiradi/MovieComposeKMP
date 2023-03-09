package com.azmiradi.moviecomposekmp.data.local

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.azmiradi.moviecomposekmp.Database

actual class SharedDatabase(private val context: Context) {

    private var database: Database? = null

    internal actual suspend fun invoke(): Database? {
        val sqliteDriver = AndroidSqliteDriver(Database.Schema, context, "test.db")
        return createDatabase(sqliteDriver)
    }

    private fun createDatabase(sqliteDriver: AndroidSqliteDriver): Database? {
        if (database == null) {
            database = Database(sqliteDriver)
        }
        return database
    }

}
