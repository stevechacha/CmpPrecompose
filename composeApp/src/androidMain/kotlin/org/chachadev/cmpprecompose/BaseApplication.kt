package org.chachadev.cmpprecompose

import android.app.Application
import org.koin.dsl.module
import presentation.app.initKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            it.modules(
               module{
                   single { this@BaseApplication.applicationContext }
               }
            )
        }
    }
}