package com.example.eventsapp.domain.model

sealed interface StateResult
object StateAwait: StateResult
object StateLoading: StateResult
object StateSuccess: StateResult
object StateError: StateResult
