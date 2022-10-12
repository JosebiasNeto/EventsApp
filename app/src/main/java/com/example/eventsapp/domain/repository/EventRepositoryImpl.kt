package com.example.eventsapp.domain.repository

import com.example.eventsapp.data.ApiService
import com.example.eventsapp.domain.model.EventModel
import com.example.eventsapp.domain.model.UserModel

class EventRepositoryImpl(private val apiService: ApiService):
    EventRepository {

    override suspend fun getAllEvents(): List<EventModel> {
        return try {
            apiService.getAllEvents()
        } catch (e: Exception){
            print(e.toString())
            listOf()
        }
    }

    override suspend fun makeCheckIn(userModel: UserModel): Boolean{
        return try {
            apiService.makeCheckIn(userModel)
            true
        } catch (e: Exception){
            print(e.toString())
            false
        }
    }
}