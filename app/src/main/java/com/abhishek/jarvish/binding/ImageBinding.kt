package com.abhishek.jarvish.binding

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

object ImageBinding {

    @BindingAdapter("app:srcCompatCustom")
    fun setImageBitmap(view: ImageView, bitmap: Bitmap?) {
        if (bitmap != null) {
            view.setImageBitmap(bitmap)
        }
    }

}