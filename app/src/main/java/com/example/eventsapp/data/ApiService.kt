package com.example.eventsapp.data

import com.example.eventsapp.domain.EventModel
import com.example.eventsapp.domain.UserModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("events")
    suspend fun getAllEvents(): List<EventModel>

    @POST("checkin")
    suspend fun makeCheckIn(@Body userModel: UserModel)

}