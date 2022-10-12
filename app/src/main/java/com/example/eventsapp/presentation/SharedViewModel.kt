package com.example.eventsapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventsapp.domain.model.*
import com.example.eventsapp.domain.repository.EventRepository
import kotlinx.coroutines.launch

class SharedViewModel(
    private val repository: EventRepository
): ViewModel() {

    val eventsList = MutableLiveData<List<EventModel>>()
    val result = MutableLiveData<StateResult>(StateAwait)

    fun getAllEvents(){
        viewModelScope.launch {
            eventsList.value = repository.getAllEvents()
        }
    }

    fun makeCheckIn(userModel: UserModel){
        result.value = StateLoading
        viewModelScope.launch {
            if(repository.makeCheckIn(userModel)){
                result.value = StateSuccess
            } else {
                result.value = StateError
            }
        }
    }

    fun resetResult(){
        result.value = StateAwait
    }

}