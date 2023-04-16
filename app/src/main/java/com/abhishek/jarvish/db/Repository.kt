package com.abhishek.jarvish.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.abhishek.jarvish.db.table.UserDetailWithRelations
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val appDatabase: AppDatabase) {

    var users: LiveData<List<UserDetailWithRelations>>? = null

    fun insertUserData(context: Context, userDetailTable: UserDetailWithRelations) {
        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.userDetailDao().insertUserDetailWithRelations(
                userDetailTable.userDetail,
                userDetailTable.mobileNumbers,
                userDetailTable.addresses,
                userDetailTable.educations
            )

        }
    }

    fun getUserList(context: Context): LiveData<List<UserDetailWithRelations>>? {
        users = appDatabase.userDetailDao().getAllUserDetailsWithRelations()
        return users
    }

    fun updateUserList(
        requireContext: Context,
        userId: String?,
        userDetailTable: UserDetailWithRelations
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.userDetailDao().updateUserDetailWithRelations(
                userDetailTable.userDetail,
                userDetailTable.mobileNumbers,
                userDetailTable.addresses,
                userDetailTable.educations
            )
        }
    }

    @dagger.Module
    @InstallIn(SingletonComponent::class)
    object RepositoryModule {
        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
            return AppDatabase.getInstance(appContext)
        }
    }
}
