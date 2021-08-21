package com.example.app.service.config

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

/** CLASSE NÃO FUNCIONAL, EM FASE DE PLANEJAMENTO */
class ConvertPhoto {

    fun convertPhotoInStringg (photoCamera:Bitmap): String{
        var photoInBytes: ByteArray
        var streamPhotoInBytes = ByteArrayOutputStream()
        photoCamera?.compress(Bitmap.CompressFormat.PNG, 70, streamPhotoInBytes)
        photoInBytes = streamPhotoInBytes.toByteArray()
        var photo = Base64.encodeToString(photoInBytes, Base64.DEFAULT)
        return photo
    }
}