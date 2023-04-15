package com.abhishek.jarvish.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abhishek.jarvish.R
import com.abhishek.jarvish.databinding.FragmentFillFormBinding
import com.abhishek.jarvish.db.table.Address
import com.abhishek.jarvish.db.table.Education
import com.abhishek.jarvish.db.table.MobileNo
import com.abhishek.jarvish.utils.Utility
import com.abhishek.jarvish.viewholder.FillFormViewModel
import java.io.IOException


class FillFormFragment : Fragment() {

    private var _binding: FragmentFillFormBinding? = null
    private val binding get() = _binding!!
    private lateinit var fillFormViewModel: FillFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_fill_form, container, false)
        //_binding = FragmentFillFormBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillFormViewModel = ViewModelProvider(this)[FillFormViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = this.fillFormViewModel
        binding.fragment = this
        fillFormViewModel.addMobileData()
        binding.executePendingBindings()


    }

    override fun onResume() {
        super.onResume()
        //fillFormViewModel.addMobileData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun saveData() {
//binding.buttonFirst.setOnClickListener {
        //  findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        //  }
    }

    fun loadImageFromGallery() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE), 100
            )
            return
        }

        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT

        takeGalleryForResult.launch(i)
    }


    private val loadImageFromGallery =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    val uri: Uri? = result.data!!.data
                    uri?.let {
                        /*val file = File(BraveFilePath.getRealPathFromURI(context!!, it))
                        if(!Utility.isFileLessThan15MB(file)){
                            val mimeType: String? = Utility.getMimeType(uri?.let { BraveFilePath.getRealPathFromURI(context!!, it) }!!)
                            if (mimeType != null && (mimeType == "image/jpeg" || mimeType == "image/png" || mimeType == "image/jpg")) {
                              //  prescriptionListGallery.put(BraveFilePath.getRealPathFromURI(context!!, uri)!!, mimeType)
                               // setGalleryAdapter()
                            }
                        }else{
                            Utility.showSnackBar(binding!!.root, "Size cannot exceed 15 MB")
                        }*/

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    var takeGalleryForResult = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            if (data != null && data.data != null) {
                val selectedImageUri = data.data
                var selectedImageBitmap: Bitmap
                try {
                    selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                        requireActivity().contentResolver, selectedImageUri
                    )
                    binding.ivUpload.setImageBitmap(
                        selectedImageBitmap
                    )
                } catch (e: IOException) {
                    binding.ivUpload.apply {
                        background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_upload)
                        setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_upload_icon))
                    }
                    Utility.showSnackBar(view?.rootView!!,"please select other Image")
                    e.printStackTrace()
                }

            }
        }
    }


}