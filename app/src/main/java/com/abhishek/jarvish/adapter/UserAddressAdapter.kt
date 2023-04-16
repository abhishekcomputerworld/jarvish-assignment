package com.abhishek.jarvish.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.jarvish.databinding.ItemAddMoreBinding
import com.abhishek.jarvish.databinding.ItemAddressBinding
import com.abhishek.jarvish.db.table.Address
import com.abhishek.jarvish.interfaces.DeleteTablesDataInterface
import com.abhishek.jarvish.utils.Constants
import com.abhishek.jarvish.viewholder.FillFormViewModel
import java.util.*

class UserAddressAdapter(
    private val context: Context,
    private val fillFormViewModel: FillFormViewModel,
    private val deleteTablesDataInterface: DeleteTablesDataInterface,
    private val userAddressList: ArrayList<Address>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class UserAddressViewHolder(val binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class AddMoreViewHolder(val binding: ItemAddMoreBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
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
            fillFormViewModel.isSubmitEnable.value =   checkIfAllFieldsFilled()
            if (getItemViewType(position) == Constants.TYPE_EDIT_VIEW) {
                val userAddressViewHolder: UserAddressViewHolder = holder as UserAddressViewHolder
                if (position != 0) {
                    userAddressViewHolder.binding.ivDelete.setOnClickListener {
                        if (userAddressList.size > 1 && position > 0) {
                            deleteTablesDataInterface.onDeleteAddressTableItem(position,userAddressList[position])
                            userAddressList.removeAt(position )
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, userAddressList.size - position)
                            fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                        }
                    }

                } else {
                    userAddressViewHolder.binding.ivDelete.visibility = View.GONE
                }

                userAddressViewHolder.binding.etHouseNo.textInputLayout.hint =
                    "House no, flat, building, apartment"
                if (!fillFormViewModel.addressList.value?.get(position)?.houseNo.isNullOrEmpty()) {
                    userAddressViewHolder.binding.etHouseNo.textInputEdittext.setText(
                        fillFormViewModel.addressList.value?.get(position)?.houseNo
                    )
                } else {
                    userAddressViewHolder.binding.etHouseNo.textInputEdittext.hint =
                        "Enter your address"
                }
                userAddressViewHolder.binding.etArea.textInputLayout.hint =
                    "Area, street, sector, village"
                if (! fillFormViewModel.addressList.value?.get(position)?.area.isNullOrEmpty()) {
                    userAddressViewHolder.binding.etArea.textInputEdittext.setText( fillFormViewModel.addressList.value?.get(position)?.area)
                } else {
                    userAddressViewHolder.binding.etArea.textInputEdittext.hint =
                        "Enter your address"
                }
                userAddressViewHolder.binding.etPinCode.textInputLayout.hint = "Pin code"
                userAddressViewHolder.binding.etPinCode.textInputEdittext.inputType =
                    InputType.TYPE_CLASS_NUMBER
                if ((userAddressList[position].pinCode ?: 0) > 0) {
                    userAddressViewHolder.binding.etPinCode.textInputEdittext.setText(
                        userAddressList[position].pinCode.toString()
                    )
                } else {
                    userAddressViewHolder.binding.etPinCode.textInputEdittext.hint =
                        "Enter your pin code"
                }
                userAddressViewHolder.binding.etCity.textInputLayout.hint = "Town/City"
                if (!userAddressList[position].houseNo.isNullOrEmpty()) {
                    userAddressViewHolder.binding.etCity.textInputEdittext.setText(userAddressList[position].city)
                } else {
                    userAddressViewHolder.binding.etCity.textInputEdittext.hint = "Enter your town"
                }
                userAddressViewHolder.binding.etState.textInputLayout.hint = "State"
                if (!userAddressList[position].houseNo.isNullOrEmpty()) {
                    userAddressViewHolder.binding.etState.textInputEdittext.setText(userAddressList[position].state)
                } else {
                    userAddressViewHolder.binding.etState.textInputEdittext.hint =
                        "Enter your state"
                }
                userAddressViewHolder.binding.etHouseNo.textInputEdittext.addTextChangedListener { s ->
                    userAddressList[position].houseNo = s.toString()
                    fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                }
                userAddressViewHolder.binding.etArea.textInputEdittext.addTextChangedListener { s ->
                    userAddressList[position].area = s.toString()
                    fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                }
                userAddressViewHolder.binding.etPinCode.textInputEdittext.addTextChangedListener { s ->
                    userAddressList[position].pinCode = s.toString().toInt()
                    fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                }
                userAddressViewHolder.binding.etCity.textInputEdittext.addTextChangedListener { s ->
                    userAddressList[position].city = s.toString()
                    fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                }
                userAddressViewHolder.binding.etState.textInputEdittext.addTextChangedListener { s ->
                    userAddressList[position].state = s.toString()
                    fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
                }

            } else {
                val addMoreViewHolder: UserAddressAdapter.AddMoreViewHolder =
                    holder as UserAddressAdapter.AddMoreViewHolder
                addMoreViewHolder.binding.addMore = "Add more address"
                addMoreViewHolder.binding.llAddMore.setOnClickListener {
                    userAddressList.add(
                        Address(
                            UUID.randomUUID().toString(),
                            "",
                            "",
                            0,
                            "",
                            "",
                            UUID.randomUUID().toString()
                        )
                    )
                    notifyItemInserted(userAddressList.size)
                  //  fillFormViewModel.isSubmitEnable.value = checkIfAllFieldsFilled()
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

    private fun checkIfAllFieldsFilled(): Boolean {
        for (address in userAddressList) {
            if (address.houseNo.isNullOrEmpty() || address.area.isNullOrEmpty()
                || address.pinCode == null || address.pinCode == 0 || address.city.isNullOrEmpty()
                || address.state.isNullOrEmpty()) {
                fillFormViewModel.isAddressDetailFilled.value= false
                return false
            }
        }
        fillFormViewModel.isAddressDetailFilled.value = true
        return fillFormViewModel.isEducationalDetailFilled.value!! && fillFormViewModel.isUserDetailFilled.value!!
    }
}