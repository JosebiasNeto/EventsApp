package com.example.eventsapp.domain.model

import com.google.gson.annotations.SerializedName

class EventModel (
    @SerializedName("date") val date: Long,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("price") val price: Float,
    @SerializedName("title") val title: String,
    @SerializedName("id") val id: Int
        ): java.io.Serializable