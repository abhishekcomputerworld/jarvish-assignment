package com.abhishek.jarvish.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.abhishek.jarvish.R
import com.abhishek.jarvish.databinding.ItemAddMoreBinding
import com.abhishek.jarvish.databinding.ItemEditFieldBinding
import com.abhishek.jarvish.databinding.ItemEducationBinding
import com.abhishek.jarvish.db.table.Education
import com.abhishek.jarvish.db.table.MobileNo
import com.abhishek.jarvish.utils.Constants

class UserEducationAdapter(
    private val context: Context,
    private val userEducationList: ArrayList<Education>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class UserEducationViewHolder(val binding: ItemEducationBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class AddMoreViewHolder(val binding: ItemAddMoreBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        if (viewType == Constants.TYPE_ADD_MORE) {
            val binding =
                ItemAddMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AddMoreViewHolder(binding)
        } else {
            val binding =
                ItemEducationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return UserEducationViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            if (getItemViewType(position) == Constants.TYPE_EDIT_VIEW) {
                val userEducationViewHolder: UserEducationViewHolder = holder as UserEducationViewHolder
                if(position!=0){
                    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_delete)
                    userEducationViewHolder.binding.tvEducation.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                }

                val drawable = ContextCompat.getDrawable(context, R.drawable.ic_drop_down)
                userEducationViewHolder.binding.etLevel.inputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                userEducationViewHolder.binding.etStream.inputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                userEducationViewHolder.binding.etStartYear.inputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                userEducationViewHolder.binding.etEndYear.inputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)

            } else{
                val addMoreViewHolder: UserEducationAdapter.AddMoreViewHolder = holder as UserEducationAdapter.AddMoreViewHolder
                addMoreViewHolder.binding.addMore = "Add more education"
                addMoreViewHolder.binding.llAddMore.setOnClickListener {
                    userEducationList.add(Education(0, "", "CSE", 2022, 2023, "Gl Bajaj"))
                    notifyItemInserted(userEducationList.size )
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return userEducationList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < userEducationList.size) {
            Constants.TYPE_EDIT_VIEW
        } else {
            Constants.TYPE_ADD_MORE
        }
    }

}