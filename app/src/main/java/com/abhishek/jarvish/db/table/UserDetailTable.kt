package com.abhishek.jarvish.db.table

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.*
import kotlin.collections.ArrayList


@Entity(tableName = "user_detail_table")
data class UserDetailTable(

    @PrimaryKey//(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var userId: String,

    @ColumnInfo(name = "profile_image")
    var profileImage: String?,

    @ColumnInfo(name = "first_name")
    var firstName: String?,

    @ColumnInfo(name = "last_name")
    var lastName: String?,

    @ColumnInfo(name = "dob")
    var dob: Date?
)

