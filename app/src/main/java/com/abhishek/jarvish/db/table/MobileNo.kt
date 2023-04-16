package com.abhishek.jarvish.db.table

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "mobileNo")
data class MobileNo(
    @PrimaryKey//(autoGenerate = true)
    @ColumnInfo(name = "mobile_id")
    var mobileId: String,

    @ColumnInfo(name = "mobile_no")
    var mobileNo: String?,

    @ColumnInfo(name = "user_id")
    var userId: String?
): Parcelable {
}
