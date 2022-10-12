package com.example.eventsapp.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.eventsapp.databinding.FragmentDetailsBinding
import com.example.eventsapp.domain.model.StateAwait
import com.example.eventsapp.domain.model.StateError
import com.example.eventsapp.domain.model.StateLoading
import com.example.eventsapp.domain.model.StateSuccess
import com.example.eventsapp.presentation.SharedViewModel
import com.example.eventsapp.utils.StringUtils.getDateTime
import com.example.eventsapp.utils.StringUtils.getPrice
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: SharedViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)

        setupObservers()
        setupUI()

        return binding.root
    }

    private fun setupObservers(){
        viewModel.result.observe(viewLifecycleOwner) {
            when(it){
                StateSuccess -> showSuccess()
                StateError -> showError()
                StateAwait -> {}
                StateLoading -> {
                    binding.loadingScreen.progressOverlay.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showSuccess(){
        binding.loadingScreen.progressOverlay.visibility = View.GONE
        Toast.makeText(context,
            "CheckIn realizado com sucesso!", Toast.LENGTH_SHORT).show()
    }

    private fun showError(){
        binding.loadingScreen.progressOverlay.visibility = View.GONE
        Toast.makeText(context,
            "Erro ao realizar checkin", Toast.LENGTH_SHORT).show()
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
        binding.checkinButton.setOnClickListener {
            showConfirmDialog()
        }
    }

    private fun showConfirmDialog(){
        requireView().findNavController().navigate(DetailsFragmentDirections
            .actionDetailsFragmentToDialog(args.event.id))
    }

    override fun onStop() {
        super.onStop()
        viewModel.resetResult()
    }
}