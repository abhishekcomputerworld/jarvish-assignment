package com.abhishek.jarvish.adapter

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.jarvish.R
import com.abhishek.jarvish.databinding.ItemAddMoreBinding
import com.abhishek.jarvish.databinding.ItemEditFieldBinding
import com.abhishek.jarvish.db.table.MobileNo
import com.abhishek.jarvish.interfaces.DeleteTablesDataInterface
import com.abhishek.jarvish.utils.Constants
import com.abhishek.jarvish.viewModel.FillFormViewModel
import java.util.*

class UserDetailAdapter(
    private val context: Context,
    private val fillFormViewModel: FillFormViewModel,
    private val deleteTablesDataInterface: DeleteTablesDataInterface,
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        with(holder) {
            fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
            if (getItemViewType(position) == Constants.TYPE_EDIT_VIEW) {
                val userAddressViewHolder: UserDetailViewHolder = holder as UserDetailViewHolder
                if (position == 0) {
                    userAddressViewHolder.binding.textInputLayout.hint = "First Name"
                    if(!fillFormViewModel.user.value?.firstName.isNullOrEmpty()){
                        userAddressViewHolder.binding.textInputEdittext.setText(fillFormViewModel.user.value?.firstName)
                    } else{
                        userAddressViewHolder.binding.textInputEdittext.hint = "Enter you first name"
                    }
                    userAddressViewHolder.binding.textInputEdittext.addTextChangedListener {s ->
                        fillFormViewModel.user.value?.firstName = s.toString()
                        fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                        userAddressViewHolder.binding.textInputLayout.error = null
                    }

                } else if (position == 1) {
                    userAddressViewHolder.binding.textInputLayout.hint = "Last name"
                    if(!fillFormViewModel.user.value?.lastName.isNullOrEmpty()){
                        userAddressViewHolder.binding.textInputEdittext.setText(fillFormViewModel.user.value?.lastName)
                    } else{
                        userAddressViewHolder.binding.textInputEdittext.hint = "Enter your last name"
                    }
                    userAddressViewHolder.binding.textInputEdittext.addTextChangedListener {s ->
                        fillFormViewModel.user.value?.lastName = s.toString()
                        fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                        userAddressViewHolder.binding.textInputLayout.error = null
                    }
                } else if (position == 2) {
                    userAddressViewHolder. binding.textInputLayout.hint = "DOB"
                    userAddressViewHolder.binding.textInputEdittext.inputType= InputType.TYPE_NULL
                    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_calender)
                    userAddressViewHolder.binding.textInputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                    userAddressViewHolder.binding.textInputEdittext.setOnClickListener {
                        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                            val date = "$dayOfMonth/${monthOfYear + 1}/$year"
                            userAddressViewHolder.binding.textInputEdittext.setText(date)
                            val calendar = Calendar.getInstance()
                            calendar.set(year, monthOfYear, dayOfMonth)

                            fillFormViewModel.user.value?.dob = calendar.time
                            userAddressViewHolder.binding.textInputLayout.error = null
                            val drawable = ContextCompat.getDrawable(context, R.drawable.ic_calender)
                            userAddressViewHolder.binding.textInputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)

                        }

                        val calendar = Calendar.getInstance()
                        val year = calendar.get(Calendar.YEAR)
                        val month = calendar.get(Calendar.MONTH)
                        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
                        val datePickerDialog = DatePickerDialog(context, dateSetListener, year, month, dayOfMonth)
                        datePickerDialog.show()
                    }
                    if((fillFormViewModel.user.value?.dob?.time?: 0) > 0){
                        userAddressViewHolder.binding.textInputEdittext.setText(
                            DateFormat.format(
                                "dd/MM/yyyy",
                                fillFormViewModel.user.value?.dob
                            ))
                        fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                    }
                    else{
                        userAddressViewHolder.binding.textInputEdittext.hint = "Select date of birth"
                    }
                } else if (position == 3) {
                    userAddressViewHolder.binding.textInputLayout.hint = "Mobile No."
                    userAddressViewHolder.binding.textInputEdittext.inputType=InputType.TYPE_CLASS_PHONE
                    if(!userMobileList[0].mobileNo.isNullOrEmpty()){
                        userAddressViewHolder.binding.textInputEdittext.setText( userMobileList[0].mobileNo)
                    } else{
                        userAddressViewHolder.binding.textInputEdittext.hint = "Enter your mobile No."
                    }
                    userAddressViewHolder.binding.textInputEdittext.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                        override fun afterTextChanged(s: Editable?) {
                            userMobileList[0].mobileNo = s.toString()
                            if (s.isNullOrEmpty()) {
                                userAddressViewHolder.binding.textInputLayout.error = "Phone number is required"
                            } else if (s.length < 10) {
                                userAddressViewHolder.binding.textInputLayout.error = "Phone number must be at least 10 digits"
                            } else if (s.length > 10) {
                                userAddressViewHolder.binding.textInputLayout.error = "Phone number cannot be more than 10 digits"
                            } else {
                                fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                                userAddressViewHolder.binding.textInputEdittext.error = null
                                userAddressViewHolder.binding.textInputLayout.error = null
                            }
                        }
                    })

                } else {
                    userAddressViewHolder.binding.textInputLayout.hint = "Mobile No."
                    if(!userMobileList[position-3]?.mobileNo.isNullOrEmpty()){
                        userAddressViewHolder.binding.textInputEdittext.setText(userMobileList[position-3]?.mobileNo)
                    } else{
                        userAddressViewHolder.binding.textInputEdittext.hint = "Enter your mobile No."
                    }
                    userAddressViewHolder.binding.textInputEdittext.inputType=InputType.TYPE_CLASS_PHONE
                    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_delete)
                    userAddressViewHolder.binding.textInputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                    userAddressViewHolder.binding.textInputEdittext.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                        override fun afterTextChanged(s: Editable?) {
                            userMobileList[position-3]?.mobileNo = s.toString()
                            if (s.isNullOrEmpty()) {
                                userAddressViewHolder.binding.textInputLayout.error = "Phone number is required"
                            } else if (s.length < 10) {
                                userAddressViewHolder.binding.textInputLayout.error = "Phone number must be at least 10 digits"
                            } else if (s.length > 10) {
                                userAddressViewHolder.binding.textInputLayout.error = "Phone number cannot be more than 10 digits"
                            } else {
                                fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                                userAddressViewHolder.binding.textInputLayout.error = null
                                userAddressViewHolder.binding.textInputEdittext.error = null
                            }
                        }
                    })


                    // Get a reference to the TextInputEditText
                    val textInputEditText = userAddressViewHolder.binding.textInputEdittext

                     // Get a reference to the delete icon drawable
                    val deleteIcon = textInputEditText.compoundDrawables[2]

                    textInputEditText.setOnTouchListener { v, event ->
                        // Check if the action is a click
                        if (event.action == MotionEvent.ACTION_UP) {
                            if (event.rawX >= textInputEditText.getRight() - deleteIcon.getBounds().width()) {
                                // User clicked on the delete icon
                                if (userMobileList.size > 1 && position > 0) {
                                    deleteTablesDataInterface.onDeleteMobileTableItem(position,userMobileList[position - 3])
                                    userMobileList.removeAt(position - 3)
                                    notifyItemRemoved(position)
                                    notifyItemRangeChanged(position, userMobileList.size - (position-3))
                                    fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                                }

                                true
                            }
                        }
                        false
                    }



                }
                userAddressViewHolder.binding.textInputEdittext.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        userAddressViewHolder.binding.textInputLayout.error = null
                        userAddressViewHolder.binding.textInputEdittext.requestFocus()
                    }
                }
            }else{
                val addMoreViewHolder: AddMoreViewHolder = holder as AddMoreViewHolder
                addMoreViewHolder.binding.addMore = "Add more mobile no."
                addMoreViewHolder.binding.llAddMore.setOnClickListener {
                    userMobileList.add(MobileNo(UUID.randomUUID().toString(),"",UUID.randomUUID().toString()))
                    notifyItemInserted(userMobileList.size + 3)
                    fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
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

  private fun checkIfAllFieldsFilled(): Boolean {
      if(fillFormViewModel.user.value?.firstName.isNullOrEmpty() || fillFormViewModel.user.value?.lastName.isNullOrEmpty() || fillFormViewModel.user.value?.dob==null){
          return false
      }
      for (mobileNo in userMobileList) {
          if (mobileNo.mobileNo.isNullOrEmpty() ) {
              return false
          }else if(mobileNo.mobileNo!!.length < 10  ||  mobileNo.mobileNo!!.length > 10){
              return false
          }
      }
      fillFormViewModel.isUserDetailFilled.value = true
      return fillFormViewModel.isEducationalDetailFilled.value!! && fillFormViewModel.isAddressDetailFilled.value!!
  }
}