package com.abhishek.jarvish.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.abhishek.jarvish.R
import com.abhishek.jarvish.adapter.UserListAdapter
import com.abhishek.jarvish.databinding.FragmentUserListBinding
import com.abhishek.jarvish.db.UserDetailViewModel
import com.abhishek.jarvish.db.table.UserDetailTable
import com.abhishek.jarvish.db.table.UserDetailWithRelations
import com.abhishek.jarvish.viewholder.FillFormViewModel
import com.abhishek.jarvish.viewholder.UserListViewModel


class UserListFragment : Fragment(), UserListAdapter.UserEditClick {

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
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListViewModel = ViewModelProvider(this)[UserListViewModel::class.java]
        setUpListener()
        binding.lifecycleOwner = this
        binding.viewModel = this.userListViewModel
        binding.userEditInterface = this
        binding.fragment = this

        userDetailSharedViewModel.getUserList(requireContext())
            ?.observe(requireActivity(), Observer {
                if (isAdded && context != null && it.isNotEmpty()) {
                  //  userDetailList = it.sortedBy { it.userDetail.firstName } as ArrayList<UserDetailWithRelations>
                    userDetailList = it.reversed() as ArrayList<UserDetailWithRelations>
                    userListViewModel.userList.value = userDetailList
                }
            })
    }

    private fun setUpListener() {
        // Set the OnQueryTextListener to filter the data when user input changes
        binding.customToolbar.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    val filteredList = userDetailList.filter { user ->
                        user.userDetail.firstName?.contains(newText, true) ?: false
                    } as ArrayList<UserDetailWithRelations>
                    binding.rvUserList.adapter?.apply {
                        if (this is UserListAdapter) {
                            val filteredList = userDetailList.filter { user ->
                                user.userDetail.firstName?.contains(newText, true) ?: false
                            } as ArrayList<UserDetailWithRelations>
                            filterList(filteredList)
                        }
                    }
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onEditClick(userData: UserDetailWithRelations?) {
        val bundle = Bundle().apply {
            putParcelable("userData", userData) // "user" is the key used to retrieve the object in the FirstFragment
        }
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment, bundle)
    }
}