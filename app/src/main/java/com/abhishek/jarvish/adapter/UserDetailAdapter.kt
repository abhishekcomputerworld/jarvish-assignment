package com.abhishek.jarvish.adapter

import android.app.DatePickerDialog
import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.jarvish.R
import com.abhishek.jarvish.databinding.ItemAddMoreBinding
import com.abhishek.jarvish.databinding.ItemEditFieldBinding
import com.abhishek.jarvish.db.table.MobileNo
import com.abhishek.jarvish.utils.Constants
import com.abhishek.jarvish.viewholder.FillFormViewModel
import java.util.*
import kotlin.collections.ArrayList

class UserDetailAdapter(
    private val context: Context,
    private val fillFormViewModel: FillFormViewModel,
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
                    userAddressViewHolder.binding.textInputLayout.hint = "First Name"
                    userAddressViewHolder.binding.textInputEdittext.hint = "Enter you first name"
                    userAddressViewHolder.binding.textInputEdittext.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                            // do nothing
                        }

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                            // update the TextView with the number of characters in the input text

                        }

                        override fun afterTextChanged(s: Editable?) {
                            // do nothing
                            fillFormViewModel.user.value?.firstName = s.toString()

                        }
                    })
                } else if (position == 1) {
                    userAddressViewHolder.binding.textInputLayout.hint = "Last name"
                    userAddressViewHolder.binding.textInputEdittext.hint = "Enter your last name"
                    userAddressViewHolder.binding.textInputEdittext.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                            // do nothing
                        }

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                            // update the TextView with the number of characters in the input text

                        }

                        override fun afterTextChanged(s: Editable?) {
                            // do nothing
                            fillFormViewModel.user.value?.lastName = s.toString()

                        }
                    })
                } else if (position == 2) {
                    userAddressViewHolder. binding.textInputLayout.hint = "DOB"
                    userAddressViewHolder.binding.textInputEdittext.hint = "Select date of birth"
                    userAddressViewHolder.binding.textInputEdittext.inputType= InputType.TYPE_NULL
                    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_calender)
                    userAddressViewHolder.binding.textInputEdittext.setOnClickListener {
                        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                            val date = "$dayOfMonth/${monthOfYear + 1}/$year"
                            userAddressViewHolder.binding.textInputEdittext.setText(date)
                            fillFormViewModel.user.value?.firstName = date.toString()
                        }

                        val calendar = Calendar.getInstance()
                        val year = calendar.get(Calendar.YEAR)
                        val month = calendar.get(Calendar.MONTH)
                        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
                        val datePickerDialog = DatePickerDialog(context, dateSetListener, year, month, dayOfMonth)
                        datePickerDialog.show()
                    }

                    userAddressViewHolder.binding.textInputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                } else if (position == 3) {
                    userAddressViewHolder.binding.textInputLayout.hint = "Mobile No."
                    userAddressViewHolder.binding.textInputEdittext.hint = "Enter your mobile No."
                    userAddressViewHolder.binding.textInputEdittext.inputType=InputType.TYPE_CLASS_PHONE
                    userAddressViewHolder.binding.textInputEdittext.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                        override fun afterTextChanged(s: Editable?) {
                            //  fillFormViewModel.mobileNoList.value?.set(0, s.toString())
                            fillFormViewModel.mobileNoList.value?.get(position-3)?.mobileNo = s.toString()
                            if (s.isNullOrEmpty()) {
                                userAddressViewHolder.binding.textInputEdittext.error = "Phone number is required"
                            } else if (s.length < 10) {
                                userAddressViewHolder.binding.textInputEdittext.error = "Phone number must be at least 10 digits"
                            } else if (s.length > 10) {
                                userAddressViewHolder.binding.textInputEdittext.error = "Phone number cannot be more than 10 digits"
                            } else {
                                userAddressViewHolder.binding.textInputEdittext.error = null
                            }
                        }
                    })

                } else {
                    userAddressViewHolder.binding.textInputLayout.hint = "Mobile No."
                    userAddressViewHolder.binding.textInputEdittext.hint = "Enter your mobile No."
                    userAddressViewHolder.binding.textInputEdittext.inputType=InputType.TYPE_CLASS_PHONE
                    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_delete)
                    userAddressViewHolder.binding.textInputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                    userAddressViewHolder.binding.textInputEdittext.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                        override fun afterTextChanged(s: Editable?) {
                            fillFormViewModel.mobileNoList.value?.get(0)?.mobileNo = s.toString()
                            if (s.isNullOrEmpty()) {
                                userAddressViewHolder.binding.textInputEdittext.error = "Phone number is required"
                            } else if (s.length < 10) {
                                userAddressViewHolder.binding.textInputEdittext.error = "Phone number must be at least 10 digits"
                            } else if (s.length > 10) {
                                userAddressViewHolder.binding.textInputEdittext.error = "Phone number cannot be more than 10 digits"
                            } else {
                                userAddressViewHolder.binding.textInputEdittext.error = null
                            }
                        }
                    })
                }


            }else{
                val addMoreViewHolder: AddMoreViewHolder = holder as AddMoreViewHolder
                addMoreViewHolder.binding.addMore = "Add more mobile no."
                addMoreViewHolder.binding.llAddMore.setOnClickListener {
                    userMobileList.add(MobileNo(UUID.randomUUID().toString(),"",UUID.randomUUID().toString()))
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