package com.example.eventsapp.presentation.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventsapp.databinding.FragmentEventsBinding
import com.example.eventsapp.presentation.SharedViewModel
import com.example.eventsapp.utils.OnItemClickListener
import com.example.eventsapp.utils.addOnItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private val viewModel: SharedViewModel by viewModel()
    private lateinit var adapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(layoutInflater)

        setupObservers()
        setupList()

        return binding.root
    }

    private fun setupList(){
        viewModel.getAllEvents()
        adapter = EventsAdapter(arrayListOf())
        binding.eventList.layoutManager = LinearLayoutManager(context)
        binding.eventList.adapter = adapter
        binding.eventList.addOnItemClickListener(object : OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {

            }
        })
    }

    private fun setupObservers(){
        viewModel.eventsList.observe(viewLifecycleOwner) {
            adapter.refreshEventsList(it)
        }
    }
}