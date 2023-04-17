package com.abhishek.jarvish.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.abhishek.jarvish.viewholder.FillFormViewModel
import org.junit.Assert.*
import org.junit.Before
import androidx.test.rule.ActivityTestRule
import com.abhishek.jarvish.activities.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.io.File

@RunWith(AndroidJUnit4::class)
class FillFormFragmentTest {

    private lateinit var fragment: FillFormFragment
    private lateinit var activity: FragmentActivity
    private lateinit var fillFormViewModel: FillFormViewModel


    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadImageFromGallery_whenPermissionDenied_shouldRequestPermission() {
      /*  val activity = activityRule.activity
        val imageLoader = Mockito.spy(activity.imageLoader)
        `when`(activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE))
            .thenReturn(PackageManager.PERMISSION_DENIED)

        imageLoader.loadImageFromGallery()

        Mockito.verify(activity).requestPermissions(
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 100
        )*/
    }

    @Test
    fun loadImageFromGallery_whenPermissionGranted_shouldStartActivityForResult() {
      /*  val activity = activityRule.activity
        val imageLoader = Mockito.spy(activity.imageLoader)
        `when`(activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE))
            .thenReturn(PackageManager.PERMISSION_GRANTED)

        imageLoader.loadImageFromGallery()

        Mockito.verify(imageLoader.takeGalleryForResult).launch(Mockito.any(Intent::class.java))
 */   }

    @Test
    fun saveImageToFileSystem_shouldCreateFileWithCorrectNameAndPath() {
        /*val activity = activityRule.activity
        val imageLoader = activity.imageLoader
        val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        val path = imageLoader.saveImageToFileSystem(bitmap)
        val expectedPath = File(
            activity.getExternalFilesDir(null), "images"
        ).absolutePath + "/" + path.split("/").last()

        val file = File(path)

        assertEquals("File should exist", true, file.exists())
        assertEquals("File should have the correct name and path", expectedPath, path)

        file.delete()*/
    }


}