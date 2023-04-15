package com.abhishek.jarvish.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mobileNo")
data class MobileNo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mobile_id")
    var mobileId: Int?,

    @ColumnInfo(name = "mobile_no")
    var mobileNo: String?,

    @ColumnInfo(name = "user_id")
    var userId: Int?
)