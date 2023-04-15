package com.abhishek.jarvish.binding

import android.content.Context
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.jarvish.adapter.UserAddressAdapter
import com.abhishek.jarvish.adapter.UserDetailAdapter
import com.abhishek.jarvish.adapter.UserEducationAdapter
import com.abhishek.jarvish.adapter.UserListAdapter
import com.abhishek.jarvish.db.table.Address
import com.abhishek.jarvish.db.table.Education
import com.abhishek.jarvish.db.table.MobileNo
import com.abhishek.jarvish.db.table.UserDetailTable

object  RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("Context", "UserMobileList")
    fun setUserDetailRecyclerView(
        recyclerView: RecyclerView, context: Context, userMobileList: ArrayList<MobileNo>?
    ) {
        if (userMobileList != null) {
            recyclerView.adapter = UserDetailAdapter(context, userMobileList)
        }
    }



    @JvmStatic
    @BindingAdapter("Context", "UserAddressList")
    fun setUserAddressRecyclerView(
        recyclerView: RecyclerView, context: Context, userAddressList: ArrayList<Address>?
    ) {
        if (userAddressList != null) {
            recyclerView.adapter = UserAddressAdapter(context, userAddressList)
        }
    }


    @JvmStatic
    @BindingAdapter("Context", "UserEducationList")
    fun setUserEducationRecyclerView(
        recyclerView: RecyclerView, context: Context, userEducationList: ArrayList<Education>?
    ) {
        if (userEducationList != null) {
            recyclerView.adapter = UserEducationAdapter(context, userEducationList)
        }
    }

    @JvmStatic
    @BindingAdapter("Context", "UserList")
    fun setUserListRecyclerView(
        recyclerView: RecyclerView, context: Context, userList: ArrayList<UserDetailTable>?
    ) {
        if (userList != null) {
            recyclerView.adapter = UserListAdapter(context, userList)
        }
    }
}