package com.abhishek.jarvish.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.abhishek.jarvish.R
import com.abhishek.jarvish.databinding.FragmentFillFormBinding
import com.abhishek.jarvish.db.UserDetailViewModel
import com.abhishek.jarvish.db.table.Address
import com.abhishek.jarvish.db.table.Education
import com.abhishek.jarvish.db.table.MobileNo
import com.abhishek.jarvish.db.table.UserDetailWithRelations
import com.abhishek.jarvish.utils.Utility
import com.abhishek.jarvish.viewholder.FillFormViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class FillFormFragment : Fragment() {

    private var _binding: FragmentFillFormBinding? = null
    private val binding get() = _binding!!
    private lateinit var fillFormViewModel: FillFormViewModel
    private val userDetailSharedViewModel: UserDetailViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_fill_form, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillFormViewModel = ViewModelProvider(this)[FillFormViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = this.fillFormViewModel
        binding.fragment = this
        if (arguments != null) {
            var user = arguments?.getParcelable<UserDetailWithRelations>("userData")
            fillFormViewModel.user.value = user?.userDetail
            fillFormViewModel.mobileNoList.value = user?.mobileNumbers as ArrayList<MobileNo>?
            fillFormViewModel.addressList.value = user?.addresses as ArrayList<Address>?
            fillFormViewModel.educationList.value = user?.educations as ArrayList<Education>?
            fillFormViewModel.userDetail.value = user
            if (!user?.userDetail?.profileImage.isNullOrEmpty()) {
                val bitmap = BitmapFactory.decodeFile(user?.userDetail?.profileImage)
                binding.ivUpload.setImageBitmap(bitmap)
            }
            fillFormViewModel.isSubmitEnable.value = true
        } else {
                fillFormViewModel.addMobileData()
        }
        binding.executePendingBindings()


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun saveData() {
        if (arguments != null) {
            userDetailSharedViewModel.updateData(
                requireContext(),
                fillFormViewModel.getUserData().value?.userDetail?.userId,
                fillFormViewModel.getUserData().value!!
            )
        } else {
            userDetailSharedViewModel.insertData(
                requireContext(),
                fillFormViewModel.getUserData().value!!
            )
        }
        if(arguments!=null){
            arguments=null
        }
        fillFormViewModel.clearAllData()
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
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

    private var takeGalleryForResult = registerForActivityResult<Intent, ActivityResult>(
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
                    // fillFormViewModel.user.value?.profileImage = ImageBitmapString.bitMapToString(selectedImageBitmap)
                    fillFormViewModel.user.value?.profileImage =
                        saveImageToFileSystem(selectedImageBitmap)
                } catch (e: IOException) {
                    binding.ivUpload.apply {
                        background =
                            ContextCompat.getDrawable(requireContext(), R.drawable.bg_upload)
                        setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.ic_upload_icon
                            )
                        )
                    }
                    Utility.showSnackBar(view?.rootView!!, "please select other Image")
                    e.printStackTrace()
                }

            }
        }
    }

    private fun saveImageToFileSystem(bitmap: Bitmap): String {
        // Get the directory for storing images
        val directory = File(context?.getExternalFilesDir(null), "images")
        if (!directory.exists()) {
            directory.mkdirs()
        }

        // Generate a unique filename for the image
        val filename = "${UUID.randomUUID()}.jpg"

        // Save the image to the filesystem
        val file = File(directory, filename)
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()

        // Return the path to the image file
        return file.absolutePath
    }


    fun closeActivity() {
        if (arguments == null) {
            requireActivity().finish()
        } else {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}