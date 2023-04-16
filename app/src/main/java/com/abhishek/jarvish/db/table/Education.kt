package com.abhishek.jarvish.db.table

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "education")
data class Education(
    @PrimaryKey  //(autoGenerate = true)
    @ColumnInfo(name = "education_id")
    var educationId: String,

    @ColumnInfo(name = "level")
    var level: String?,

    @ColumnInfo(name = "stream")
    var stream: String?,

    @ColumnInfo(name = "start_year")
    var startYear: Int?,

    @ColumnInfo(name = "end_year")
    var endYear: Int?,

    @ColumnInfo(name = "college_name")
    var college: String?,

    @ColumnInfo(name = "user_id")
    var userId: String?
): Parcelable {
}
