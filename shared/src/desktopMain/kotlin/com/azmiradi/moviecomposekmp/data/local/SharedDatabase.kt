package com.azmiradi.moviecomposekmp.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.azmiradi.moviecomposekmp.Database
import java.io.File

actual class SharedDatabase {
    private var database: Database? = null

    internal actual suspend fun invoke(): Database? {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:database.db")
        if (!File("database.db").exists()) {
            Database.Schema.create(driver)
        }
        return createDatabase(driver)
    }

    private fun createDatabase(driver: SqlDriver): Database? {
        if (database == null) {
            database = Database(driver)
        }
        return database
    }
}