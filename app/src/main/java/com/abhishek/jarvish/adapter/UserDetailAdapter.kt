package com.abhishek.jarvish.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.jarvish.databinding.ItemEditFieldBinding
import com.abhishek.jarvish.db.table.MobileNo

class UserDetailAdapter(
    private val context: Context,
    private val userMobileList: ArrayList<MobileNo>
) :
    RecyclerView.Adapter<UserDetailAdapter.UserDetailViewHolder>() {

    inner class UserDetailViewHolder(val binding: ItemEditFieldBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserDetailViewHolder {
        val binding =
            ItemEditFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserDetailViewHolder, position: Int) {
        with(holder) {


        }
    }

    override fun getItemCount(): Int {
        return userMobileList.size + 3
    }
}