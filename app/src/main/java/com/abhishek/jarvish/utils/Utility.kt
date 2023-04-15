package com.abhishek.jarvish.utils

import android.graphics.Color
import android.view.View
import android.webkit.MimeTypeMap
import com.google.android.material.snackbar.Snackbar
import java.io.File

object Utility {
    fun isFileLessThan15MB(file: File): Boolean {
        val maxFileSize = 15 * 1024 * 1024
        val l: Long = file.length()
        val fileSize = l.toString()
        val finalFileSize = fileSize.toInt()
        return finalFileSize >= maxFileSize
    }

    fun getMimeType(url: String): String? {
        val extension = url.substring(url.lastIndexOf("."))
        val mimeTypeMap = MimeTypeMap.getFileExtensionFromUrl(extension)
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(mimeTypeMap)
    }

    fun showSnackBar(view: View, message: String) {
        try {
            val snackBar = Snackbar.make(
                view, message, Snackbar.LENGTH_LONG
            )
            snackBar.view.setBackgroundColor(Color.parseColor("#0E3F6C"))
            snackBar.setTextColor(Color.parseColor("#FFFFFF"))
            snackBar.setTextMaxLines(3)
            snackBar.view.minimumHeight = 100 // here
            snackBar.show()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

    }


}