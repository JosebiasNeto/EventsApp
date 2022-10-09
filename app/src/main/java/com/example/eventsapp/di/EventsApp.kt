package com.example.eventsapp.di

import android.app.Application
import com.example.eventsapp.di.Modules.data
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class EventsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@EventsApp)
            modules(data)
        }
    }
}