package com.example.eventsapp.services

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.view.View
import androidx.core.content.FileProvider
import com.example.eventsapp.BuildConfig
import java.io.File
import java.io.FileOutputStream
import java.util.*

class ShareScreenService(private val context: Context) {

    private val FILE_PROVIDER = "shareable_files"
    private val IMAGE_NAME = "event.jpg"
    private val AUTHORITY_SUFFIX = ".provider"

    fun shareEvent(view: View): Intent? {
        val bitmap = createBitmap(view)
        val file = saveImage(bitmap)
        return file?.let { shareImage(it) }
    }

    private fun createBitmap(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(
            view.width, view.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun saveImage(image: Bitmap): File? {
        val file = File(context.externalCacheDir, FILE_PROVIDER)
        return try {
            file.mkdir()
            val imageFile = File(file, IMAGE_NAME)
            val stream = FileOutputStream(imageFile)
            image.compress(Bitmap.CompressFormat.JPEG, 90, stream)
            stream.flush()
            stream.close()
            imageFile
        } catch (e: Exception){
            Log.e("TAG", "Erro ao salvar imagem, codigo: " + e.message)
            null
        }
    }

    private fun shareImage(file: File): Intent{
        val uri = FileProvider.getUriForFile(Objects.requireNonNull(context),
            BuildConfig.APPLICATION_ID + AUTHORITY_SUFFIX, file)
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "image/jpeg"
        }
        return intent
    }
}