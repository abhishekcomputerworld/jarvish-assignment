package com.abhishek.jarvish.interfaces

import com.abhishek.jarvish.db.table.Address
import com.abhishek.jarvish.db.table.Education
import com.abhishek.jarvish.db.table.MobileNo
import com.abhishek.jarvish.db.table.UserDetailTable


interface DeleteTablesDataInterface{
    fun onDeleteMobileTableItem(position: Int,mobileNo : MobileNo)
    fun onDeleteEducationTableItem(position: Int,education : Education)
    fun onDeleteAddressTableItem(position: Int,address: Address)
}