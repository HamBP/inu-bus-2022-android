package org.algosketch.inubus.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InuRunApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}