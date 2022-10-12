package com.example.eventsapp.presentation.details

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.eventsapp.databinding.ConfirmCheckinDialogBinding
import com.example.eventsapp.domain.model.UserModel
import com.example.eventsapp.presentation.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfimCheckInDialog: DialogFragment() {

    private lateinit var binding: ConfirmCheckinDialogBinding
    private val viewModel: SharedViewModel by sharedViewModel()
    private val args: ConfimCheckInDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ConfirmCheckinDialogBinding.inflate(layoutInflater)

        setupDialogUI()

        return binding.root
    }

    private fun setupDialogUI(){
        binding.confirmButton.setOnClickListener {
            if(invalidNameAndEmail()){
                Toast.makeText(context, "Preencha corretamente", Toast.LENGTH_SHORT).show()
            } else {
                confirmCheckIn()
                dismiss()
            }
        }
    }

    private fun invalidNameAndEmail(): Boolean{
        return binding.name.text.isEmpty() || binding.email.text.isEmpty()
    }

    private fun confirmCheckIn(){
        viewModel.makeCheckIn(UserModel(
            args.eventId,
            binding.name.text.toString(),
            binding.email.text.toString()
        ))
    }

    override fun onResume() {
        super.onResume()
        val params = dialog!!.window!!.attributes
        params.width = LayoutParams.MATCH_PARENT
        params.height = LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params
    }
}