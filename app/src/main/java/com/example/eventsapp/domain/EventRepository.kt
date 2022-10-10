package com.example.eventsapp.domain

import com.example.eventsapp.data.ApiService
import com.example.eventsapp.domain.model.EventModel
import com.example.eventsapp.domain.model.UserModel

class EventRepository(private val apiService: ApiService) {

    suspend fun getAllEvents(): List<EventModel> {
        return try {
            apiService.getAllEvents()
        } catch (e: Exception){
            print(e.toString())
            listOf()
        }
    }

    suspend fun makeCheckIn(userModel: UserModel){
        try {
            apiService.makeCheckIn(userModel)
        } catch (e: Exception){
            print(e.toString())
        }
    }
}