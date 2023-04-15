package com.abhishek.jarvish.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.jarvish.databinding.ItemEditFieldBinding
import com.abhishek.jarvish.db.table.Address

class UserAddressAdapter(
    private val context: Context,
    private val userMobileList: ArrayList<Address>
) :
    RecyclerView.Adapter<UserAddressAdapter.UserAddressViewHolder>() {

    inner class UserAddressViewHolder(val binding: ItemEditFieldBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAddressViewHolder {
        val binding =
            ItemEditFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserAddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAddressViewHolder, position: Int) {
        with(holder) {


        }
    }

    override fun getItemCount(): Int {
        return userMobileList.size
    }
}