package com.abhishek.jarvish.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.abhishek.jarvish.R
import com.abhishek.jarvish.binding.RecyclerViewBinding
import com.abhishek.jarvish.databinding.FragmentFillFormBinding
import com.abhishek.jarvish.db.table.Address
import com.abhishek.jarvish.db.table.Education
import com.abhishek.jarvish.db.table.MobileNo
import com.abhishek.jarvish.viewholder.FillFormViewModel

class FillFormFragment : Fragment() {

    private var _binding: FragmentFillFormBinding? = null
    private val binding get() = _binding!!
    private lateinit var fillFormViewModel: FillFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          _binding = FragmentFillFormBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillFormViewModel = ViewModelProvider(this)[FillFormViewModel::class.java]
        fillFormViewModel.addressList.value?.add(Address(0, 0, "", 201305, "noida", "Up"))
        fillFormViewModel.educationList.value?.add(Education(0, "", "CSE", 2022, 2023, "Gl Bajaj"))
        fillFormViewModel.mobileNoList.value?.add(MobileNo(0, "8178279133"))
        //binding.buttonFirst.setOnClickListener {
        //  findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        //  }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun saveData() {

    }
}