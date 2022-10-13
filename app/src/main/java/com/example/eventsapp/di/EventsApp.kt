package com.example.eventsapp.di

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import com.example.eventsapp.di.Modules.data
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class EventsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        startKoin{
            androidContext(this@EventsApp)
            modules(data)
        }
    }
}