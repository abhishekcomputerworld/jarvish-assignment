package com.abhishek.jarvish.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.abhishek.jarvish.R
import com.abhishek.jarvish.databinding.ItemAddMoreBinding
import com.abhishek.jarvish.databinding.ItemEducationBinding
import com.abhishek.jarvish.db.table.Education
import com.abhishek.jarvish.interfaces.DeleteTablesDataInterface
import com.abhishek.jarvish.utils.Constants
import com.abhishek.jarvish.viewModel.FillFormViewModel
import java.util.*


class UserEducationAdapter(
    private val context: Context,
    private val fillFormViewModel: FillFormViewModel,
    private val deleteTablesDataInterface: DeleteTablesDataInterface,
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
            if (getItemViewType(position) == Constants.TYPE_EDIT_VIEW) {
                val userEducationViewHolder: UserEducationViewHolder =
                    holder as UserEducationViewHolder
                if (position != 0) {
                    userEducationViewHolder.binding.ivDelete.setOnClickListener {
                        if (userEducationList.size > 1 && position > 0) {
                            deleteTablesDataInterface.onDeleteEducationTableItem(position,userEducationList[position])
                            userEducationList.removeAt(position)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, userEducationList.size - position)
                            fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                        }
                    }
                } else {
                    userEducationViewHolder.binding.ivDelete.visibility = View.GONE
                }
                userEducationViewHolder.binding.etLevel.textInputLayout.hint = "Level"
                if (!userEducationList[position].level.isNullOrEmpty()) {
                    userEducationViewHolder.binding.etLevel.textInputEdittext.setText(
                        userEducationList[position].level
                    )
                } else {
                    userEducationViewHolder.binding.etLevel.textInputEdittext.hint = "Select level"
                }
                userEducationViewHolder.binding.etStream.textInputLayout.hint = "Stream"
                if (!userEducationList[position].stream.isNullOrEmpty()) {
                    userEducationViewHolder.binding.etStream.textInputEdittext.setText(
                        userEducationList[position].stream
                    )
                } else {
                    userEducationViewHolder.binding.etStream.textInputEdittext.hint =
                        "Select stream"
                }
                userEducationViewHolder.binding.etStartYear.textInputLayout.hint = "Start year"
                if ((userEducationList[position].startYear ?: 0) > 0) {
                    userEducationViewHolder.binding.etStartYear.textInputEdittext.setText(
                        userEducationList[position].startYear.toString()
                    )
                } else {
                    userEducationViewHolder.binding.etStartYear.textInputEdittext.hint =
                        "Select start year"
                }
                userEducationViewHolder.binding.etEndYear.textInputLayout.hint = "End year"
                userEducationViewHolder.binding.etStartYear.textInputLayout.hint = "Start year"
                if ((userEducationList[position].endYear ?: 0) > 0) {
                    userEducationViewHolder.binding.etEndYear.textInputEdittext.setText(
                        userEducationList[position].endYear.toString()
                    )
                } else {
                    userEducationViewHolder.binding.etEndYear.textInputEdittext.hint =
                        "Select end year"
                }
                userEducationViewHolder.binding.etCollege.textInputLayout.hint =
                    "Institute/Collage name"
                if (!userEducationList[position].college.isNullOrEmpty()) {
                    userEducationViewHolder.binding.etCollege.textInputEdittext.setText(
                        userEducationList[position].college.toString()
                    )
                } else {
                    userEducationViewHolder.binding.etCollege.textInputEdittext.hint =
                        "Enter collage name"
                }


                /*     val drawable = ContextCompat.getDrawable(context, R.drawable.ic_drop_down)
                     userEducationViewHolder.binding.etLevel.textInputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                     userEducationViewHolder.binding.etStream.textInputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                     userEducationViewHolder.binding.etStartYear.textInputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
                     userEducationViewHolder.binding.etEndYear.textInputEdittext.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
     */

                userEducationViewHolder.binding.etLevel.textInputEdittext.setAdapter(
                    ArrayAdapter.createFromResource(
                        context,
                        R.array.education_levels,
                        android.R.layout.simple_dropdown_item_1line
                    )
                )
                userEducationViewHolder.binding.etStream.textInputEdittext.setAdapter(
                    ArrayAdapter.createFromResource(
                        context,
                        R.array.education_streams,
                        android.R.layout.simple_dropdown_item_1line
                    )
                )
                userEducationViewHolder.binding.etStartYear.textInputEdittext.setAdapter(
                    ArrayAdapter.createFromResource(
                        context,
                        R.array.education_start_years,
                        android.R.layout.simple_dropdown_item_1line
                    )
                )
                //   userEducationViewHolder.binding.etEndYear.textInputEdittext.setAdapter(ArrayAdapter.createFromResource(context, R.array.education_end_years, android.R.layout.simple_dropdown_item_1line))
                val educationEndYears = context.resources.getStringArray(R.array.education_end_years)
                userEducationViewHolder.binding.etStartYear.textInputEdittext.setOnItemClickListener { parent, view, position, id ->
                    val startYear = parent.getItemAtPosition(position).toString()
                    userEducationViewHolder.binding.etEndYear.textInputEdittext.setAdapter(
                        ArrayAdapter(
                            context,
                            android.R.layout.simple_dropdown_item_1line,
                            educationEndYears.filter { it >= startYear })
                    )
                }


                userEducationViewHolder.binding.etEndYear.textInputEdittext.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            val endYear = parent?.getItemAtPosition(position).toString()
                            val startYear =
                                userEducationViewHolder.binding.etStartYear.textInputEdittext.text.toString()
                            if (endYear < startYear) {
                                userEducationViewHolder.binding.etEndYear.textInputEdittext.error =
                                    "End year cannot be less than start year"
                            } else {
                                userEducationViewHolder.binding.etEndYear.textInputEdittext.error =
                                    null
                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                    }


                userEducationViewHolder.binding.etLevel.textInputEdittext.addTextChangedListener { s ->
                    userEducationList[position].level = s.toString()
                    fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                    userEducationViewHolder.binding.etLevel.textInputLayout.error = null
                }
                userEducationViewHolder.binding.etStream.textInputEdittext.addTextChangedListener { s ->
                    userEducationList[position].stream = s.toString()
                    fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                    userEducationViewHolder.binding.etStream.textInputLayout.error = null
                }
                userEducationViewHolder.binding.etStartYear.textInputEdittext.addTextChangedListener { s ->
                    userEducationList[position].startYear = s.toString().toInt()
                    fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                    userEducationViewHolder.binding.etStartYear.textInputLayout.error = null
                }
                userEducationViewHolder.binding.etEndYear.textInputEdittext.addTextChangedListener { s ->
                    userEducationList[position].endYear = s.toString().toInt()
                    fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                    userEducationViewHolder.binding.etEndYear.textInputLayout.error = null
                }
                userEducationViewHolder.binding.etCollege.textInputEdittext.addTextChangedListener { s ->
                    userEducationList[position].college = s.toString()
                    fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                    userEducationViewHolder.binding.etCollege.textInputLayout.error = null
                }

                userEducationViewHolder.binding.etLevel.textInputEdittext.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        userEducationViewHolder.binding.etLevel.textInputLayout.error = null
                        userEducationViewHolder.binding.etLevel.textInputEdittext.requestFocus()
                    }
                }
                userEducationViewHolder.binding.etStream.textInputEdittext.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        userEducationViewHolder.binding.etStream.textInputLayout.error = null
                        userEducationViewHolder.binding.etStream.textInputEdittext.requestFocus()
                    }
                }
                userEducationViewHolder.binding.etStartYear.textInputEdittext.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        userEducationViewHolder.binding.etStartYear.textInputLayout.error = null
                        userEducationViewHolder.binding.etStartYear.textInputEdittext.requestFocus()
                    }
                }
                userEducationViewHolder.binding.etEndYear.textInputEdittext.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        userEducationViewHolder.binding.etEndYear.textInputLayout.error = null
                        userEducationViewHolder.binding.etEndYear.textInputEdittext.requestFocus()
                    }
                }
                userEducationViewHolder.binding.etCollege.textInputEdittext.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        userEducationViewHolder.binding.etCollege.textInputLayout.error = null
                        userEducationViewHolder.binding.etCollege.textInputEdittext.requestFocus()
                    }
                }

            } else {
                val addMoreViewHolder: UserEducationAdapter.AddMoreViewHolder =
                    holder as UserEducationAdapter.AddMoreViewHolder
                addMoreViewHolder.binding.addMore = "Add more education"
                addMoreViewHolder.binding.llAddMore.setOnClickListener {
                    userEducationList.add(
                        Education(
                            UUID.randomUUID().toString(),
                            "",
                            "",
                            0,
                            0,
                            "",
                            UUID.randomUUID().toString()
                        )
                    )
                    notifyItemInserted(userEducationList.size)
                    fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
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

    private fun checkIfAllFieldsFilled(): Boolean {
        for (address in userEducationList) {
            if (address.level.isNullOrEmpty() || address.stream.isNullOrEmpty()
                || address.startYear == 0 || address.endYear==0
                || address.college.isNullOrEmpty()
            ) {
                return false
            }
        }
        fillFormViewModel.isEducationalDetailFilled.value = true
        return fillFormViewModel.isAddressDetailFilled.value!! && fillFormViewModel.isUserDetailFilled.value!!
    }
}