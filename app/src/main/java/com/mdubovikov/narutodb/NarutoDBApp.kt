package com.mdubovikov.narutodb

import android.app.Application
import com.mdubovikov.narutodb.di.ApplicationComponent
import com.mdubovikov.narutodb.di.DaggerApplicationComponent

class NarutoDBApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}