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
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)
class FillFormFragmentTest {

    private lateinit var fragment: FillFormFragment
    private lateinit var activity: FragmentActivity
    private lateinit var fillFormViewModel: FillFormViewModel

}