package com.azmiradi.moviecomposekmp.data.local

import com.azmiradi.moviecomposekmp.Database

expect class SharedDatabase {
    internal suspend fun invoke(): Database?
}