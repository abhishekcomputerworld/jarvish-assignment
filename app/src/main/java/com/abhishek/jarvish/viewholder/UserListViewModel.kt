package com.abhishek.jarvish.viewholder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhishek.jarvish.db.table.UserDetailTable

class UserListViewModel : ViewModel() {
     val userList = MutableLiveData<ArrayList<UserDetailTable>>()

}