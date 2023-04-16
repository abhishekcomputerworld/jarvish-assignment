package com.abhishek.jarvish.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.abhishek.jarvish.db.table.UserDetailTable
import com.abhishek.jarvish.db.table.UserDetailWithRelations

class UserDetailViewModel : ViewModel() {

    var userList: LiveData<List<UserDetailWithRelations>>? = null

    fun insertData(context: Context, userDetailTable: UserDetailWithRelations) {
        Repository.insertUserData(context,userDetailTable)
    }

    fun getUserList(context: Context): LiveData<List<UserDetailWithRelations>>? {
        userList = Repository.getUserList(context)
        return userList
    }

    fun updateData(requireContext: Context, userId: String?, value: UserDetailWithRelations) {
        Repository.updateUserList(requireContext,userId,value)
    }

}