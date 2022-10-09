package com.example.eventsapp.domain

import com.google.gson.annotations.SerializedName

class UserModel (
    @SerializedName("eventId") val eventId: Int,
    @SerializedName("name") val userName: String,
    @SerializedName("email") val userEmail: String
        )