package com.azmiradi.moviecomposekmp

import android.app.Application

var appObj: Application? = null

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appObj = this
    }
}


