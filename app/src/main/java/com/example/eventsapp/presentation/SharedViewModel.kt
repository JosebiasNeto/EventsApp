package com.example.eventsapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventsapp.domain.model.EventModel
import com.example.eventsapp.domain.model.UserModel
import com.example.eventsapp.domain.repository.EventRepository
import kotlinx.coroutines.launch

class SharedViewModel(
    private val repository: EventRepository
): ViewModel() {

    val eventsList = MutableLiveData<List<EventModel>>()

    fun getAllEvents(){
        viewModelScope.launch {
            eventsList.value = repository.getAllEvents()
        }
    }

    fun makeCheckIn(userModel: UserModel){
        viewModelScope.launch {
            repository.makeCheckIn(userModel)
        }
    }

}