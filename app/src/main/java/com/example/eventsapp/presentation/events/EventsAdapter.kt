package com.example.eventsapp.presentation.events

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eventsapp.databinding.EventItemBinding
import com.example.eventsapp.domain.model.EventModel
import com.squareup.picasso.Picasso
import retrofit2.http.Url
import java.net.URL
import java.sql.Date
import java.text.SimpleDateFormat

class EventsAdapter(private var eventsList: ArrayList<EventModel>):
    RecyclerView.Adapter<EventsAdapter.EventsHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsHolder {
        val binding = EventItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return EventsHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsHolder, position: Int) =
        holder.bind(eventsList[position])

    override fun getItemCount(): Int = eventsList.size

    class EventsHolder(private val itemBinding: EventItemBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(eventModel: EventModel){
            Picasso.get().load(eventModel.image).noFade().into(itemBinding.eventImage)
            itemBinding.eventTitle.text = eventModel.title
            itemBinding.eventDate.text = getDateTime(eventModel.date.toString())
            itemBinding.eventPrice.text = eventModel.price.toString().replace(".", ",")
        }

        @SuppressLint("SimpleDateFormat")
        private fun getDateTime(s: String): String {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(s.toLong() * 1000)
            return sdf.format(netDate)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshEventsList(newEventsList: List<EventModel>){
        this.eventsList.apply {
            clear()
            addAll(newEventsList)
            notifyDataSetChanged()
        }
    }
}