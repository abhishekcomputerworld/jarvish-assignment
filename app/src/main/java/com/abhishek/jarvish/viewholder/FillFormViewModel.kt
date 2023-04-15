package com.abhishek.jarvish.viewholder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhishek.jarvish.db.table.Address
import com.abhishek.jarvish.db.table.Education
import com.abhishek.jarvish.db.table.MobileNo

class FillFormViewModel : ViewModel() {
   

     val isSubmitEnable = MutableLiveData(false)
     val mobileNoList = MutableLiveData<ArrayList<MobileNo>>()
     val addressList = MutableLiveData<ArrayList<Address>>()
     val educationList = MutableLiveData<ArrayList<Education>>()



     fun addMobileData() {
          addressList.value=arrayListOf(Address(0, 0, "", 201305, "noida", "Up"))
          educationList.value=arrayListOf(Education(0, "", "CSE", 2022, 2023, "Gl Bajaj"))
          mobileNoList.value=arrayListOf(MobileNo(0, "8178279133"))
     }


}