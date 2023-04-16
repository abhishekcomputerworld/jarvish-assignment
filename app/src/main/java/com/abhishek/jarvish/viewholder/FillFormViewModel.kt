package com.abhishek.jarvish.viewholder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhishek.jarvish.db.table.*
import java.util.*

class FillFormViewModel : ViewModel() {


    val isSubmitEnable = MutableLiveData(false)
    val mobileNoList = MutableLiveData<ArrayList<MobileNo>?>()
    val addressList = MutableLiveData<ArrayList<Address>?>()
    val educationList = MutableLiveData<ArrayList<Education>?>()
    val user = MutableLiveData<UserDetailTable?>()
    var userDetail = MutableLiveData<UserDetailWithRelations?>()

    fun addMobileData() {
        if (user.value == null) {
            user.value = UserDetailTable(UUID.randomUUID().toString(), "", "", "", null)
        }
        if (addressList.value == null) {
            addressList.value = arrayListOf(
                Address(
                    UUID.randomUUID().toString(), "", "", 0, "", "",
                    UUID.randomUUID().toString()
                )
            )
        }

        if (educationList.value == null) {
            educationList.value = arrayListOf(
                Education(
                    UUID.randomUUID().toString(), "", "", 0, 0, "",
                    UUID.randomUUID().toString()
                )
            )
        }
        if (mobileNoList.value == null) {
            mobileNoList.value = arrayListOf(
                MobileNo(
                    UUID.randomUUID().toString(), "",
                    UUID.randomUUID().toString()
                )
            )
        }
        userDetail.value?.userDetail = user.value!!
        userDetail.value?.addresses = addressList.value!!
        userDetail.value?.mobileNumbers = mobileNoList.value!!
        userDetail.value?.educations = educationList.value!!
        isSubmitEnable.value = true
    }


    fun getUserData(): MutableLiveData<UserDetailWithRelations?> {
        userDetail.value = UserDetailWithRelations(
            user.value!!, mobileNoList.value!!, addressList.value!!, educationList.value!!

        )
        return userDetail
    }

    fun clearAllData() {
        user.value = null
        mobileNoList.value = null
        addressList.value = null
        educationList.value = null
        userDetail.value = null
        isSubmitEnable.value = false
    }


}