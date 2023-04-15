package com.abhishek.jarvish.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.jarvish.R
import com.abhishek.jarvish.databinding.ItemAddMoreBinding
import com.abhishek.jarvish.databinding.ItemAddressBinding
import com.abhishek.jarvish.db.table.Address
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder) {
            if (getItemViewType(position) == Constants.TYPE_EDIT_VIEW) {
                val userAddressViewHolder: UserAddressViewHolder = holder as UserAddressViewHolder
                if (position != 0) {
                    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_delete)
                   /* userAddressViewHolder.binding.tvAddress.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        drawable,
                        null
                    )*/
                    userAddressViewHolder.binding.ivDelete.setOnClickListener {
                        userAddressList.removeAt(position)
                        notifyItemRemoved(position)
                    }

                }else{
                    userAddressViewHolder.binding.ivDelete.visibility= View.GONE
                }

                userAddressViewHolder.binding.etHouseNo.textInputLayout.hint = "House no, flat, building, apartment"
                userAddressViewHolder.binding.etHouseNo.textInputEdittext.hint = "Enter your address"
                userAddressViewHolder.binding.etArea.textInputLayout.hint = "Area, street, sector, village"
                userAddressViewHolder.binding.etArea.textInputEdittext.hint = "Enter your address"
                userAddressViewHolder.binding.etPinCode.textInputLayout.hint = "Pin code"
                userAddressViewHolder.binding.etPinCode.textInputEdittext.hint = "Enter your pin code"
                userAddressViewHolder.binding.etCity.textInputLayout.hint = "Town/City"
                userAddressViewHolder.binding.etCity.textInputEdittext.hint = "Enter your town"
                userAddressViewHolder.binding.etState.textInputLayout.hint = "State"
                userAddressViewHolder.binding.etState.textInputEdittext.hint = "Enter your state"

            } else {
                val addMoreViewHolder: UserAddressAdapter.AddMoreViewHolder =
                    holder as UserAddressAdapter.AddMoreViewHolder
                addMoreViewHolder.binding.addMore = "Add more address"
                addMoreViewHolder.binding.llAddMore.setOnClickListener {
                    userAddressList.add(Address(position, 0, "", 201305, "noida", "Up",0))
                    notifyItemInserted(userAddressList.size)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return userAddressList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < userAddressList.size) {
            Constants.TYPE_EDIT_VIEW
        } else {
            Constants.TYPE_ADD_MORE
        }
    }
}