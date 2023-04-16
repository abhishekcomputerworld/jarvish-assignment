package com.abhishek.jarvish.db.table

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "address")
data class Address(
    @PrimaryKey//(autoGenerate = true)
    @ColumnInfo(name = "address_id")
    var addressId: String,

    @ColumnInfo(name = "house_no")
    var houseNo: String?,

    @ColumnInfo(name = "area")
    var area: String?,

    @ColumnInfo(name = "pin_code")
    var pinCode: Int?,

    @ColumnInfo(name = "city")
    var city: String?,

    @ColumnInfo(name = "state")
    var state: String?,

    @ColumnInfo(name = "user_id")
    var userId: String?
): Parcelable {
}
