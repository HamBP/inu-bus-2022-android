package org.algosketch.inubus.global.util

import android.app.Application
import android.content.Context
import org.algosketch.inubus.di.dataSourceModule
import org.algosketch.inubus.di.repositoryModule
import org.algosketch.inubus.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: MyApplication
        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()

            androidContext(this@MyApplication)

            androidFileProperties()

            modules(dataSourceModule)
            modules(repositoryModule)
            modules(useCaseModule)
        }
    }
}