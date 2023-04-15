package com.abhishek.jarvish.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class Address(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "house_no")
    val userId: Int,
    @ColumnInfo(name = "area")
    val address: String,
    @ColumnInfo(name = "pin_code")
    val pinCode: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "state")
    val state: String
)