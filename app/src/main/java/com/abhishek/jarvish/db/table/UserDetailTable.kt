package com.abhishek.jarvish.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity(tableName = "user_detail_table")
data class UserDetailTable(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var user_id: Int?,

    @ColumnInfo(name = "profile_image")
    var profileImage: String?,

    @ColumnInfo(name = "first_name")
    var firstName: String?,

    @ColumnInfo(name = "last_name")
    var lastName: String?,

    @ColumnInfo(name = "dob")
    var dob: Int?,

    @ColumnInfo(name = "mobile_no")
    var mobileNo: Int?,

    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    var mobileList: List<MobileNo>,

    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    var addressList: List<Address>,


    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    var educationList: List<Education>


)
