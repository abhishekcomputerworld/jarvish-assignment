package com.abhishek.jarvish.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class Address(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    @ColumnInfo(name = "house_no")
    var userId: Int?,
    @ColumnInfo(name = "area")
    var address: String?,
    @ColumnInfo(name = "pin_code")
    var pinCode: Int?,
    @ColumnInfo(name = "city")
    var city: String?,
    @ColumnInfo(name = "state")
    var state: String?
)