package com.abhishek.jarvish.db.table

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserDetailWithRelations(
    @Embedded var userDetail: UserDetailTable,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id"
    )
    var mobileNumbers: List<MobileNo>,

    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id"
    )
    var addresses: List<Address>,

    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id"
    )
    var educations: List<Education>
) : Parcelable {
}
