package com.abhishek.jarvish.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.abhishek.jarvish.db.table.UserDetailWithRelations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository {
    companion object {

        var appDatabase: AppDatabase? = null

        var users: LiveData<List<UserDetailWithRelations>>? = null

        private fun initializeDB(context: Context): AppDatabase {
            return AppDatabase.getInstance(context)
        }

        fun insertUserData(context: Context, userDetailTable: UserDetailWithRelations) {
            appDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                appDatabase!!.userDetailDao().insertUserDetailWithRelations(
                    userDetailTable.userDetail,
                    userDetailTable.mobileNumbers,
                    userDetailTable.addresses,
                    userDetailTable.educations
                )

            }
        }

        fun getUserList(context: Context): LiveData<List<UserDetailWithRelations>>? {
            appDatabase = initializeDB(context)
            users = appDatabase!!.userDetailDao().getAllUserDetailsWithRelations()
            return users
        }

        fun updateUserList(
            requireContext: Context, userId: String?, userDetailTable: UserDetailWithRelations
        ) {
            appDatabase = initializeDB(requireContext)
            CoroutineScope(Dispatchers.IO).launch {
                appDatabase!!.userDetailDao().updateUserDetailWithRelations(
                    userDetailTable.userDetail,
                    userDetailTable.mobileNumbers,
                    userDetailTable.addresses,
                    userDetailTable.educations
                )
            }
        }
    }


}