package com.candra.ptpn_employee_training.ui.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.candra.ptpn_employee_training.R
import com.candra.ptpn_employee_training.data.employe
import com.candra.ptpn_employee_training.databinding.AddDialogLayoutBinding
import com.candra.ptpn_employee_training.databinding.ListEmployeeBinding
import com.candra.ptpn_employee_training.helper.Constant.EXTRA_EMPLOYEE
import com.candra.ptpn_employee_training.helper.Constant.JABATAN_EMPLOYEE
import com.candra.ptpn_employee_training.helper.Constant.KARYAWAN
import com.candra.ptpn_employee_training.helper.Constant.NAMA
import com.candra.ptpn_employee_training.helper.Constant.NKR_EMPLOYEE
import com.candra.ptpn_employee_training.helper.Constant.UNIT_KEBUN_EMPLOYEE
import com.candra.ptpn_employee_training.helper.Utils.showEmptyText
import com.candra.ptpn_employee_training.ui.adapter.EmployeeAdapter
import com.candra.ptpn_employee_training.ui.viewmodel.EmployeeViewModel
import com.candra.ptpn_employee_training.ui.viewmodel.TrainingViewModel
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeActivity: AppCompatActivity()
{
    private lateinit var binding: ListEmployeeBinding
    private val isValid = mutableListOf(false,false,false,false)
    private val employeeViewModel by viewModels<EmployeeViewModel>()
    private val adapterEmployee by lazy { EmployeeAdapter(::onClickData,::onDelete) }
    private val trainingViewModel by viewModels<TrainingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addEmployee.apply {
            setOnClickListener {
                showDialog()
            }
            text = getString(R.string.add_employee, KARYAWAN)
        }
        setAdapter()
        observerAllData()
        fabConstraintLayout()

        binding.btnDeleteAll.setOnClickListener {
            clickDeleteAllData()
        }

        binding.edtSearch.doAfterTextChanged {
            val lowercase = it.toString()
            val convertLowerCase = "%$lowercase%"
            if (lowercase.isEmpty()){
                showBtnClose(false)
                observerAllData()
            }else{
                showBtnClose(true)
                employeeViewModel.searchEmployee(convertLowerCase).observe(this,this::showAllDataEmployee)
            }
        }

        binding.closeBtnSearch.setOnClickListener {
            binding.tilSearchEmployee.editText?.text?.clear()
        }

        binding.contentImage.setOnClickListener {
            finish()
        }

    }

    private fun showBtnClose(isShow: Boolean){
        binding.closeBtnSearch.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    private fun clickDeleteAllData(){
        val builder = AlertDialog.Builder(this@EmployeeActivity)
        builder.apply {
            setPositiveButton(getString(R.string.yes)){_,_ ->
                employeeViewModel.deleteAllEmployee()
                trainingViewModel.deleteAllOFTraining()
                Toast.makeText(this@EmployeeActivity,getString(R.string.succesfully_delete_all_data),Toast.LENGTH_SHORT).show()
            }
            setNegativeButton(getString(R.string.no)){_,_ -> }
            setTitle(getString(R.string.title_delete_all))
            setMessage(getString(R.string.message_delete_all))
            create().show()
        }
    }

    private fun setAdapter(){
        with(binding){
            recyclerviewListEmployee.apply {
                layoutManager = LinearLayoutManager(this@EmployeeActivity)
                adapter = adapterEmployee
            }
        }
    }

    private fun observerAllData(){
        employeeViewModel.showAllEmployee.observe(this,this::showAllDataEmployee)
    }

    private fun showAllDataEmployee(listData: List<employe>){
        if (listData.isEmpty()){
            binding.btnDeleteAll.visibility = View.GONE
            showEmptyText(true,binding.textNothingData,binding.nestedScrollView)
        }else{
            showEmptyText(false,binding.textNothingData,binding.nestedScrollView)
            adapterEmployee.temptAllData(listData)
            binding.btnDeleteAll.visibility = View.VISIBLE
        }
    }

    private fun showDialog(){
        val dialog = Dialog(this@EmployeeActivity)
        val dialogBinding = AddDialogLayoutBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(dialogBinding.root)
        }
        with(dialogBinding){
            closeBtn.setOnClickListener {
                dialog.dismiss()
            }

            titleAdd.text = getString(R.string.add_employee, KARYAWAN)

            edtTextYourName.doAfterTextChanged {
                val nameValid = it?.isNotBlank() ?: false
                isValid[0] = nameValid
                tilInputName.apply {
                    if (!nameValid) error = getString(R.string.not_be_empty_employee, NAMA)
                    isErrorEnabled = !nameValid
                }
                validateButton(btnConfirmation)
            }
            edtTextYourNkr.doAfterTextChanged {
                val nkrValid = it?.isNotBlank() ?: false
                isValid[1] = nkrValid
                tilNkr.apply {
                    if (!nkrValid) error = getString(R.string.not_be_empty_employee, NKR_EMPLOYEE)
                    isErrorEnabled = !nkrValid
                }
                validateButton(btnConfirmation)
            }

            edtYourJabatan.doAfterTextChanged {
                val jabatanValid = it?.isNotBlank() ?: false
                isValid[2] = jabatanValid
                tilJabatan.apply {
                    if (!jabatanValid) error = getString(R.string.not_be_empty_employee,
                        JABATAN_EMPLOYEE)
                    isErrorEnabled = !jabatanValid
                }
                validateButton(btnConfirmation)
            }

            edtYourUnitKebun.doAfterTextChanged {
                val unitKebunValid = it?.isNotBlank() ?: false
                isValid[3] = unitKebunValid
                tilUnitKebun.apply {
                    if (!unitKebunValid) error = getString(R.string.not_be_empty_employee,
                        UNIT_KEBUN_EMPLOYEE)
                    isErrorEnabled = !unitKebunValid
                }
                validateButton(btnConfirmation)
            }
            btnConfirmation.setOnClickListener {
                val name = tilInputName.editText?.text.toString()
                val nkr = tilNkr.editText?.text.toString()
                val jabatan = tilJabatan.editText?.text.toString()
                val unitKebun = tilUnitKebun.editText?.text.toString()
                val employee = employe(0,name,nkr,jabatan,unitKebun)
                employeeViewModel.insertEmployee(employee)
                dialog.dismiss()
                Toast.makeText(this@EmployeeActivity,getString(R.string.successfully_add_toast),Toast.LENGTH_SHORT).show()
            }
        }

        dialog.apply {
            show()
            window?.apply {
                setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                attributes.windowAnimations = R.style.DialogAnimation
                setGravity(Gravity.BOTTOM)
            }
        }
    }

    private fun validateButton(btnValid: MaterialButton){
        btnValid.isEnabled = isValid.filter { it }.size == 4
    }

    private fun onClickData(data: employe){
        Intent(this@EmployeeActivity,DetailEmployee::class.java).apply {
            putExtra(EXTRA_EMPLOYEE,data)
        }.also { startActivity(it) }
    }

    private fun
            onDelete(data: employe){
        val builder = AlertDialog.Builder(this@EmployeeActivity)
        builder.apply {
            setPositiveButton(getString(R.string.yes)){_,_ ->
                employeeViewModel.deleteItemEmployee(data)
                val nameEmployee = "%${data.name}%"
                trainingViewModel.deleteAllTraining(nameEmployee)
                Toast.makeText(this@EmployeeActivity,getString(R.string.succesfully_delete_toast,data.name),Toast.LENGTH_SHORT).show()
            }
            setNegativeButton(getString(R.string.no)){_,_ -> }
            setTitle(getString(R.string.title_dialog,data.name))
            setMessage(getString(R.string.message_dialog,data.name))
            create().show()
        }
    }

    private fun fabConstraintLayout() {
        binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY < oldScrollY) binding.addEmployee.extend() else binding.addEmployee.shrink()
        })
    }
}