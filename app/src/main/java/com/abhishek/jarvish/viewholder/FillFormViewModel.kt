package com.abhishek.jarvish.viewholder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhishek.jarvish.db.table.*
import java.util.*

class FillFormViewModel : ViewModel() {


    val isSubmitEnable = MutableLiveData(false)
    val mobileNoList = MutableLiveData<ArrayList<MobileNo>>()
    val addressList = MutableLiveData<ArrayList<Address>>()
    val educationList = MutableLiveData<ArrayList<Education>>()
    val user = MutableLiveData<UserDetailTable>()
    val userDetail = MutableLiveData<UserDetailWithRelations>()

    val imageBase64 = MutableLiveData<String>()


    fun addMobileData() {
        user.value=UserDetailTable( UUID.randomUUID().toString(),"","","", Date())
        addressList.value = arrayListOf(Address(UUID.randomUUID().toString(), "", "", 201305, "noida", "Up",UUID.randomUUID().toString()))
        educationList.value = arrayListOf(Education(UUID.randomUUID().toString(), "", "CSE", 2022, 2023, "Gl Bajaj",UUID.randomUUID().toString()))
        mobileNoList.value = arrayListOf(MobileNo(UUID.randomUUID().toString(), "8178279133",UUID.randomUUID().toString()))
        isSubmitEnable.value = true
    }

    fun getUserData(): MutableLiveData<UserDetailWithRelations> {
        userDetail.value = UserDetailWithRelations(
            user.value!!,
            mobileNoList.value!!,
            addressList.value!!,
            educationList.value!!

        )
        return userDetail
    }
}