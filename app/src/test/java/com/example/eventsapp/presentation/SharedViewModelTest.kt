package com.example.eventsapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.eventsapp.domain.model.*
import com.example.eventsapp.domain.repository.EventRepositoryImpl
import com.example.eventsapp.services.ShareScreenService
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SharedViewModelTest{

    lateinit var viewModel: SharedViewModel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    val repository = mockk<EventRepositoryImpl>()
    val shareService = mockk<ShareScreenService>()
    val eventsListMock = mockk<Observer<List<EventModel>>>()
    val resultMock = mockk<Observer<StateResult>>(relaxed = true)
    val eventListSlot = slot<List<EventModel>>()
    val eventResponseList = mockk<List<EventModel>>()
    val userModelMock = mockk<UserModel>(relaxed = true)
    val resultList = mutableListOf<StateResult>()

    @OptIn(DelicateCoroutinesApi::class)
    val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup(){
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = spyk(SharedViewModel(repository, shareService))
        viewModel.eventsList.observeForever(eventsListMock)
        viewModel.result.observeForever(resultMock)
    }

    @Test
    fun getAllEvents_perfectCase_success() = runBlocking {
        coEvery {
            repository.getAllEvents()
        } returns eventResponseList

        viewModel.getAllEvents()
        delay(500L)

        verify {
            eventsListMock.onChanged(capture(eventListSlot))
        }

        assert(eventListSlot.captured == eventResponseList)
    }

    @Test
    fun makeCheckIn_perfectCase_success() = runBlocking {
        coEvery {
            repository.makeCheckIn(any())
        } returns true

        viewModel.makeCheckIn(userModelMock)
        delay(500L)

        verify {
            resultMock.onChanged(capture(resultList))
        }

        if(resultList.size >= 2){
            assert(resultList[0] == StateAwait)
            assert(resultList[1] == StateLoading)
            assert(resultList[2] == StateSuccess)
        }
    }

    @Test
    fun makeCheckIn_perfectCase_error() = runBlocking {
        coEvery {
            repository.makeCheckIn(any())
        } returns false

        viewModel.makeCheckIn(userModelMock)
        delay(500L)

        verify {
            resultMock.onChanged(capture(resultList))
        }

        if(resultList.size >= 2){
            assert(resultList[0] == StateAwait)
            assert(resultList[1] == StateLoading)
            assert(resultList[2] == StateError)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}