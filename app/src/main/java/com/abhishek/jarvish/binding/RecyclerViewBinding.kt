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
import com.abhishek.jarvish.db.table.UserDetailWithRelations
import com.abhishek.jarvish.interfaces.DeleteTablesDataInterface
import com.abhishek.jarvish.viewModel.FillFormViewModel

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("Context", "ViewModel","Interface", "UserMobileList")
    fun setUserDetailRecyclerView(
        recyclerView: RecyclerView,
        context: Context,
        fillFormViewModel: FillFormViewModel,
        deleteTablesDataInterface: DeleteTablesDataInterface,
        userMobileList: ArrayList<MobileNo>?
    ) {
        if (userMobileList != null) {
            recyclerView.adapter = UserDetailAdapter(context, fillFormViewModel, deleteTablesDataInterface,userMobileList)
        }
    }


    @JvmStatic
    @BindingAdapter("Context", "ViewModel","Interface", "UserAddressList")
    fun setUserAddressRecyclerView(
        recyclerView: RecyclerView,
        context: Context,
        fillFormViewModel: FillFormViewModel,
        deleteTablesDataInterface: DeleteTablesDataInterface,
        userAddressList: ArrayList<Address>?
    ) {
        if (userAddressList != null) {
            recyclerView.adapter = UserAddressAdapter(context, fillFormViewModel,deleteTablesDataInterface, userAddressList)
        }
    }


    @JvmStatic
    @BindingAdapter("Context", "ViewModel","Interface", "UserEducationList")
    fun setUserEducationRecyclerView(
        recyclerView: RecyclerView,
        context: Context,
        fillFormViewModel: FillFormViewModel,
        deleteTablesDataInterface: DeleteTablesDataInterface,
        userEducationList: ArrayList<Education>?
    ) {
        if (userEducationList != null) {
            recyclerView.adapter =
                UserEducationAdapter(context, fillFormViewModel, deleteTablesDataInterface,userEducationList)
        }
    }

    @JvmStatic
    @BindingAdapter("Context", "EditClickInterface", "UserList")
    fun setUserListRecyclerView(
        recyclerView: RecyclerView,
        context: Context,
        userEditClickInterface: UserListAdapter.UserEditClick,
        userList: ArrayList<UserDetailWithRelations>?
    ) {
        if (userList != null) {
            recyclerView.adapter = UserListAdapter(context, userEditClickInterface, userList)
        }
    }
}