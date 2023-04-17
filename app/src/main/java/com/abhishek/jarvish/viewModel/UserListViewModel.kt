package com.abhishek.jarvish.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhishek.jarvish.db.table.UserDetailWithRelations


class UserListViewModel : ViewModel() {
    val userList = MutableLiveData<ArrayList<UserDetailWithRelations>>()

}