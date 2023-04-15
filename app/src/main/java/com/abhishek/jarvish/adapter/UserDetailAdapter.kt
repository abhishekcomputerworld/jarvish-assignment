package com.abhishek.jarvish.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.jarvish.R
import com.abhishek.jarvish.databinding.ItemAddMoreBinding
import com.abhishek.jarvish.databinding.ItemEditFieldBinding
import com.abhishek.jarvish.db.table.MobileNo
import com.abhishek.jarvish.utils.Constants

class UserDetailAdapter(
    private val context: Context,
    private val userMobileList: ArrayList<MobileNo>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class UserDetailViewHolder(val binding: ItemEditFieldBinding) :
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
        } else { val binding =
            ItemEditFieldBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserDetailViewHolder(binding)}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder) {
            if (getItemViewType(position) == Constants.TYPE_EDIT_VIEW) {
                val userAddressViewHolder: UserDetailViewHolder = holder as UserDetailViewHolder
                if (position == 0) {
                    userAddressViewHolder.binding.inputLayoutEdittext.isHintAnimationEnabled=false
                    userAddressViewHolder.binding.inputLayoutEdittext.hint = "First Name"
                    userAddressViewHolder.binding.inputEdittext.hint = "Enter you first name"
                } else if (position == 1) {
                    userAddressViewHolder.binding.inputLayoutEdittext.hint = "Last name"
                    userAddressViewHolder.binding.inputEdittext.hint = "Enter your last name"

                } else if (position == 2) {
                    userAddressViewHolder. binding.inputLayoutEdittext.hint = "DOB"
                    userAddressViewHolder.binding.inputEdittext.hint = "Select date of birth"
                    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_calender)
                    userAddressViewHolder.binding.inputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)

                } else if (position == 3) {
                    userAddressViewHolder.binding.inputLayoutEdittext.hint = "Mobile No."
                    userAddressViewHolder.binding.inputEdittext.hint = "Enter your mobile No."

                } else {
                    userAddressViewHolder.binding.inputLayoutEdittext.hint = "Mobile No."
                    userAddressViewHolder.binding.inputEdittext.hint = "Enter your mobile No."
                    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_delete)
                    userAddressViewHolder.binding.inputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                }
            }else{
                val addMoreViewHolder: AddMoreViewHolder = holder as AddMoreViewHolder
                addMoreViewHolder.binding.addMore = "Add more mobile no."
                addMoreViewHolder.binding.llAddMore.setOnClickListener {
                    userMobileList.add(MobileNo(1,""))
                    notifyItemInserted(userMobileList.size + 3)
                }

            }

        }
    }

    override fun getItemCount(): Int {
        return userMobileList.size + 3+1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position <userMobileList.size + 3) {
            Constants.TYPE_EDIT_VIEW
        } else {
            Constants.TYPE_ADD_MORE
        }
    }
}