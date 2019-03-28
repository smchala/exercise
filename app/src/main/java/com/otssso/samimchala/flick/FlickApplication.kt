package com.otssso.samimchala.flick

import android.app.Application

class FlickApplication : Application() {

    companion object {

        lateinit var instance: FlickApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}