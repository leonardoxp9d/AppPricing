package com.example.app.service.config

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.app.service.constants.RequestCodeConstants
/** CLASSE NÃO FUNCIONAL, EM FASE DE PLANEJAMENTO */
class SelectPhoto {
    
    fun capturePhotoCamera (activity: Activity) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(activity.packageManager)?.also {
                activity.startActivityForResult(takePictureIntent, RequestCodeConstants.requestCodeCamera)
            }
        }
    }

    fun selectPhotoGallery (activity: Activity) {
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI).also { takePictureIntent ->
            takePictureIntent.resolveActivity(activity.packageManager)?.also {
                activity.startActivityForResult(takePictureIntent, RequestCodeConstants.requestCodeGallery)
            }
        }
    }
}