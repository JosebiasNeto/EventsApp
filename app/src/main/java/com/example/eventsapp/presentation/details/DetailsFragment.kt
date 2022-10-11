package com.example.eventsapp.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.eventsapp.databinding.FragmentDetailsBinding
import com.example.eventsapp.utils.StringUtils.getDateTime
import com.example.eventsapp.utils.StringUtils.getPrice
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)

        setupUI()

        return binding.root
    }

    private fun setupUI(){
        Picasso.get().load(args.event.image).noFade().into(binding.eventImage)
        binding.eventTitle.text = args.event.title
        binding.eventDescription.text = args.event.description
        binding.eventDate.text = getDateTime(args.event.date.toString())
        binding.eventPrice.text = getPrice(args.event.price)
        binding.backButton.setOnClickListener {
            requireView().findNavController().popBackStack()
        }
    }
}