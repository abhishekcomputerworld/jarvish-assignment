package com.abhishek.jarvish.binding

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter

object ImageBinding {

    @JvmStatic
    @BindingAdapter("app:srcCompatCustom")
    fun setImageBitmap(view: ImageView, bitmap: Bitmap?) {
        if (bitmap != null) {
            view.setImageBitmap(bitmap)
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(view: ImageView, url: String?) {
        if(!url.isNullOrEmpty()){
            val bitmap = BitmapFactory.decodeFile(url)
            view.setImageBitmap(bitmap)
        }

    }

}