package com.example.eventsapp.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

object Permission {

    fun requestPermission(context: Context, fragment: Fragment){
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) -> {}
            else -> Manifest.permission.READ_EXTERNAL_STORAGE.requestPermission(fragment)
        }
    }

    private fun String.requestPermission(fragment: Fragment){
        val requestPermissionLauncher =
            fragment.registerForActivityResult(
                ActivityResultContracts.RequestPermission()){}
        requestPermissionLauncher.launch(this)
    }

}