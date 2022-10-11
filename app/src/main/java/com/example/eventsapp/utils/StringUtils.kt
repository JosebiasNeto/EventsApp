package com.example.eventsapp.utils

import android.annotation.SuppressLint
import java.sql.Date
import java.text.SimpleDateFormat

object StringUtils {

    @SuppressLint("SimpleDateFormat")
    fun getDateTime(s: String): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val netDate = Date(s.toLong() * 1000)
        return sdf.format(netDate)
    }

    fun getPrice(price: Float): String {
        return price.toString().replace(".", ",")
    }

}