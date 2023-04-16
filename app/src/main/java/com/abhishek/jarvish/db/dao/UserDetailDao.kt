package com.abhishek.jarvish.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abhishek.jarvish.db.table.*

@Dao
interface UserDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetail(userDetail: UserDetailTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMobileNo(mobileNo: MobileNo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: Address)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEducation(education: Education)

    @Transaction
    suspend fun insertUserDetailWithRelations(
        userDetail: UserDetailTable,
        mobileNumbers: List<MobileNo>,
        addresses: List<Address>,
        educations: List<Education>
    ) {
        insertUserDetail(userDetail)
        mobileNumbers.forEach { mobileNo ->
            insertMobileNo(mobileNo.apply {
                userId = userDetail.userId
            })
        }
        addresses.forEach { address -> insertAddress(address.apply { userId = userDetail.userId }) }
        educations.forEach { education ->
            insertEducation(education.apply {
                userId = userDetail.userId
            })
        }
    }

    // Retrieval methods
    @Query("SELECT * FROM user_detail_table")
    fun getAllUserDetails(): LiveData<List<UserDetailTable>>

    @Query("SELECT * FROM mobileNo WHERE user_id = :userId")
    fun getMobileNumbersByUserId(userId: Int): LiveData<List<MobileNo>>


    @Query("SELECT * FROM address WHERE user_id = :userId")
    fun getAddressesByUserId(userId: Int): LiveData<List<Address>>

    @Query("SELECT * FROM education WHERE user_id = :userId")
    fun getEducationDetailsByUserId(userId: Int): LiveData<List<Education>>

    @Transaction
    @Query("SELECT * FROM user_detail_table WHERE user_id = :userId")
    fun getUserDetailWithRelations(userId: Int): LiveData<UserDetailWithRelations?>

    @Transaction
    @Query("SELECT * FROM user_detail_table")
    fun getAllUserDetailsWithRelations(): LiveData<List<UserDetailWithRelations>>

    // Update method
    @Transaction
    suspend fun updateUserDetailWithRelations(
        userDetail: UserDetailTable,
        mobileNumbers: List<MobileNo>,
        addresses: List<Address>,
        educations: List<Education>
    ) {
        insertUserDetail(userDetail)
        mobileNumbers.forEach { mobileNo ->
            insertMobileNo(mobileNo.apply {
                userId = userDetail.userId
            })
        }
        addresses.forEach { address -> insertAddress(address.apply { userId = userDetail.userId }) }
        educations.forEach { education ->
            insertEducation(education.apply {
                userId = userDetail.userId
            })
        }
    }

}