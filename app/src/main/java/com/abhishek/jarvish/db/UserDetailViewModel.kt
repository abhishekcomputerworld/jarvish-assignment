package com.abhishek.jarvish.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.abhishek.jarvish.db.table.UserDetailWithRelations
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class UserDetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var userList: LiveData<List<UserDetailWithRelations>>? = null

    fun insertData(@ApplicationContext context: Context, userDetailTable: UserDetailWithRelations) {
        repository.insertUserData(context, userDetailTable)
    }

    fun getUserList(@ApplicationContext context: Context): LiveData<List<UserDetailWithRelations>>? {
        userList = repository.getUserList(context)
        return userList
    }

    fun updateData(@ActivityContext requireContext: Context, userId: String?, value: UserDetailWithRelations) {
        repository.updateUserList(requireContext, userId, value)
    }
}





