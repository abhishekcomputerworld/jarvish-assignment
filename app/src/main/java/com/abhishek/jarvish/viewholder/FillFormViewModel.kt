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
        user.value=UserDetailTable(1,"","","", Date())
        addressList.value = arrayListOf(Address(0, 0, "", 201305, "noida", "Up",0))
        educationList.value = arrayListOf(Education(0, "", "CSE", 2022, 2023, "Gl Bajaj",0))
        mobileNoList.value = arrayListOf(MobileNo(0, "8178279133",0))
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