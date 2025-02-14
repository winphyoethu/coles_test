package com.winphyoethu.coles_test

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ColesApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}