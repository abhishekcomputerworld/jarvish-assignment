package com.abhishek.jarvish.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.jarvish.databinding.ItemEditFieldBinding
import com.abhishek.jarvish.db.table.Education

class UserEducationAdapter(
    private val context: Context,
    private val userMobileList: ArrayList<Education>
) :
    RecyclerView.Adapter<UserEducationAdapter.UserEducationViewHolder>() {

    inner class UserEducationViewHolder(val binding: ItemEditFieldBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserEducationViewHolder {
        val binding =
            ItemEditFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserEducationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserEducationViewHolder, position: Int) {
        with(holder) {


        }
    }

    override fun getItemCount(): Int {
        return userMobileList.size + 3
    }
}