package com.abhishek.jarvish.binding

import android.content.Context
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.jarvish.adapter.UserAddressAdapter
import com.abhishek.jarvish.adapter.UserDetailAdapter
import com.abhishek.jarvish.adapter.UserEducationAdapter
import com.abhishek.jarvish.adapter.UserListAdapter
import com.abhishek.jarvish.db.table.*
import com.abhishek.jarvish.viewholder.FillFormViewModel

object  RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("Context", "ViewModel", "UserMobileList")
    fun setUserDetailRecyclerView(
        recyclerView: RecyclerView, context: Context,fillFormViewModel: FillFormViewModel, userMobileList: ArrayList<MobileNo>?
    ) {
        if (userMobileList != null) {
            recyclerView.adapter = UserDetailAdapter(context,fillFormViewModel, userMobileList)
        }
    }



    @JvmStatic
    @BindingAdapter("Context","ViewModel", "UserAddressList")
    fun setUserAddressRecyclerView(
        recyclerView: RecyclerView, context: Context, fillFormViewModel: FillFormViewModel, userAddressList: ArrayList<Address>?
    ) {
        if (userAddressList != null) {
            recyclerView.adapter = UserAddressAdapter(context,fillFormViewModel, userAddressList)
        }
    }


    @JvmStatic
    @BindingAdapter("Context","ViewModel",  "UserEducationList")
    fun setUserEducationRecyclerView(
        recyclerView: RecyclerView, context: Context,fillFormViewModel: FillFormViewModel, userEducationList: ArrayList<Education>?
    ) {
        if (userEducationList != null) {
            recyclerView.adapter = UserEducationAdapter(context, fillFormViewModel,userEducationList)
        }
    }

    @JvmStatic
    @BindingAdapter("Context", "UserList")
    fun setUserListRecyclerView(
        recyclerView: RecyclerView, context: Context, userList: ArrayList<UserDetailWithRelations>?
    ) {
        if (userList != null) {
            recyclerView.adapter = UserListAdapter(context, userList)
        }
    }
}