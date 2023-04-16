package com.abhishek.jarvish.db.table

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserDetailWithRelations(
    @Embedded val userDetail: UserDetailTable,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id"
    )
    val mobileNumbers: List<MobileNo>,

    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id"
    )
    val addresses: List<Address>,

    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id"
    )
    val educations: List<Education>
) : Parcelable {
}
