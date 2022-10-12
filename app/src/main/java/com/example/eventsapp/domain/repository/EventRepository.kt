package com.example.eventsapp.domain.repository

import com.example.eventsapp.domain.model.EventModel
import com.example.eventsapp.domain.model.UserModel

interface EventRepository {
    suspend fun getAllEvents(): List<EventModel>
    suspend fun makeCheckIn(userModel: UserModel): Boolean
}