package com.abhishek.jarvish.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "education")
data class Education(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "level")
    var level: String?,

    @ColumnInfo(name = "stream")
    var stream: String?,

    @ColumnInfo(name = "start_year")
    var start_year: Int?,

    @ColumnInfo(name = "end_year")
    var end_year: Int?,

    @ColumnInfo(name = "college_name")
    var College: String?
)