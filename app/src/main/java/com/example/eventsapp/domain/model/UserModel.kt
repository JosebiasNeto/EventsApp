package com.example.eventsapp.domain.model

import com.google.gson.annotations.SerializedName

class UserModel (
    @SerializedName("eventId") var eventId: Int,
    @SerializedName("name") val userName: String,
    @SerializedName("email") val userEmail: String
        )