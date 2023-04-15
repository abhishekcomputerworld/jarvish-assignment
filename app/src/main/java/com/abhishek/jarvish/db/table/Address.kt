package com.abhishek.jarvish.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class Address(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "address_id")
    var addressId: Int?,

    @ColumnInfo(name = "house_no")
    var houseNo: Int?,

    @ColumnInfo(name = "area")
    var area: String?,

    @ColumnInfo(name = "pin_code")
    var pinCode: Int?,

    @ColumnInfo(name = "city")
    var city: String?,

    @ColumnInfo(name = "state")
    var state: String?,

    @ColumnInfo(name = "user_id")
    var userId: Int?
)