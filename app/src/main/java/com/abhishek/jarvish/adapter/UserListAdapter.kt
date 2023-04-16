package com.abhishek.jarvish.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.jarvish.databinding.ItemUserBinding
import com.abhishek.jarvish.db.table.UserDetailWithRelations

class UserListAdapter(
    private val context: Context,
    private var userEditClickInterface:UserEditClick,
    private var userList: ArrayList<UserDetailWithRelations>
) :
    RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    private var searchList: ArrayList<UserDetailWithRelations> = arrayListOf()

    inner class UserListViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        with(holder) {
            binding.user = userList[position]
            binding.ivEdit.setOnClickListener {
                userEditClickInterface.onEditClick(userList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    interface UserEditClick {
        fun onEditClick(userData: UserDetailWithRelations?)
    }

    fun filterList(filteredList: ArrayList<UserDetailWithRelations>) {
        userList = filteredList
        notifyDataSetChanged()
    }

}