package com.abhishek.jarvish.viewholder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhishek.jarvish.db.table.*
import java.util.*
import kotlin.collections.ArrayList

class FillFormViewModel : ViewModel() {


    val isSubmitEnable = MutableLiveData(false)
    val mobileNoList = MutableLiveData<ArrayList<MobileNo>>()
    val addressList = MutableLiveData<ArrayList<Address>>()
    val educationList = MutableLiveData<ArrayList<Education>>()
    val user = MutableLiveData<UserDetailTable>()
    val userDetail = MutableLiveData<UserDetailWithRelations>()

    val imageBase64 = MutableLiveData<String>()

    fun addMobileData() {
        user.value=UserDetailTable( UUID.randomUUID().toString(),"","","", null)
        addressList.value = arrayListOf(Address(
            UUID.randomUUID().toString(), "", "", 0, "", "",
            UUID.randomUUID().toString()))
        educationList.value = arrayListOf(Education(
            UUID.randomUUID().toString(), "", "", 0, 0, "",
            UUID.randomUUID().toString()))
        mobileNoList.value = arrayListOf(MobileNo(
            UUID.randomUUID().toString(), "",
            UUID.randomUUID().toString()))
        isSubmitEnable.value = true
    }


    fun getUserData(): MutableLiveData<UserDetailWithRelations> {
        userDetail.value = UserDetailWithRelations(
            user.value!!, mobileNoList.value!!, addressList.value!!, educationList.value!!

        )
        return userDetail
    }
}