package com.abhishek.jarvish.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDetailDao {

   /* @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bestPackageResults: BestPackageResults)


    @Query("DELETE FROM recent_package_table WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * from recent_package_table ORDER by iid DESC")
    fun getRecentPackageList(): LiveData<List<BestPackageResults>>?
*/
}