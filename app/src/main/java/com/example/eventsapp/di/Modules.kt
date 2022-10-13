package com.example.eventsapp.di

import com.example.eventsapp.data.RetrofitBuilder
import com.example.eventsapp.domain.repository.EventRepository
import com.example.eventsapp.domain.repository.EventRepositoryImpl
import com.example.eventsapp.presentation.SharedViewModel
import com.example.eventsapp.services.ShareScreenService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {

    val data = module {

        factory<EventRepository> { EventRepositoryImpl(RetrofitBuilder.eventsRepositoriesApi) }
        factory { ShareScreenService(androidContext()) }

        viewModel {
            SharedViewModel(get(), get())
        }
    }
}