package com.abhishek.jarvish.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.abhishek.jarvish.R
import com.abhishek.jarvish.databinding.FragmentUserListBinding
import com.abhishek.jarvish.db.UserDetailViewModel
import com.abhishek.jarvish.db.table.UserDetailTable
import com.abhishek.jarvish.db.table.UserDetailWithRelations
import com.abhishek.jarvish.viewholder.FillFormViewModel
import com.abhishek.jarvish.viewholder.UserListViewModel


class UserListFragment : Fragment() {

    private lateinit var  userDetailList: ArrayList<UserDetailWithRelations>
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private lateinit var userListViewModel: UserListViewModel
    private val userDetailSharedViewModel: UserDetailViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_user_list, container, false)
        binding.fragment = this
// _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListViewModel = ViewModelProvider(this)[UserListViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = this.userListViewModel
        binding.fragment = this
        userDetailSharedViewModel.getUserList(requireContext())
            ?.observe(requireActivity(), Observer {
                if (isAdded && context != null && it.isNotEmpty()) {
                    userDetailList = it as ArrayList<UserDetailWithRelations>
                    userListViewModel.userList.value=userDetailList
                }
            })



       // binding.buttonSecond.setOnClickListener {
          //  findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
       // }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}