package com.duen.exypnos

import android.app.Application
import com.google.android.material.color.DynamicColors

class ExypnosApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}