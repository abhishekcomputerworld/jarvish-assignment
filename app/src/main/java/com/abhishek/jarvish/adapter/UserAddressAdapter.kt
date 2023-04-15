package com.abhishek.jarvish.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.jarvish.R
import com.abhishek.jarvish.databinding.ItemAddMoreBinding
import com.abhishek.jarvish.databinding.ItemAddressBinding
import com.abhishek.jarvish.databinding.ItemEditFieldBinding
import com.abhishek.jarvish.db.table.Address
import com.abhishek.jarvish.db.table.MobileNo
import com.abhishek.jarvish.utils.Constants

class UserAddressAdapter(
    private val context: Context,
    private val userAddressList: ArrayList<Address>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class UserAddressViewHolder(val binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class AddMoreViewHolder(val binding: ItemAddMoreBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if (viewType == Constants.TYPE_ADD_MORE) {
            val binding =
                ItemAddMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AddMoreViewHolder(binding)
        } else {
            val binding =
                ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return UserAddressViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder) {
            if (getItemViewType(position) == Constants.TYPE_EDIT_VIEW) {
                val userAddressViewHolder: UserAddressViewHolder = holder as UserAddressViewHolder
                    if(position!=0){
                        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_delete)
                        userAddressViewHolder.binding.tvAddress.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                    }
            } else{
                val addMoreViewHolder: UserAddressAdapter.AddMoreViewHolder = holder as UserAddressAdapter.AddMoreViewHolder
                addMoreViewHolder.binding.addMore = "Add more address"
                addMoreViewHolder.binding.llAddMore.setOnClickListener {
                    userAddressList.add(Address(0, 0, "", 201305, "noida", "Up"))
                    notifyItemInserted(userAddressList.size )
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return userAddressList.size+1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < userAddressList.size) {
            Constants.TYPE_EDIT_VIEW
        } else {
            Constants.TYPE_ADD_MORE
        }
    }
}