package com.candra.ptpn_employee_training.ui.activity

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.candra.ptpn_employee_training.R
import com.candra.ptpn_employee_training.data.employe
import com.candra.ptpn_employee_training.data.training
import com.candra.ptpn_employee_training.databinding.ActivityDetailEmployeeBinding
import com.candra.ptpn_employee_training.databinding.AddTrainingDialogBinding
import com.candra.ptpn_employee_training.helper.Constant.DATE_FORMAT
import com.candra.ptpn_employee_training.helper.Constant.EXTRA_EMPLOYEE
import com.candra.ptpn_employee_training.helper.Constant.EXTRA_TRAINING
import com.candra.ptpn_employee_training.helper.Constant.PELATIHAN
import com.candra.ptpn_employee_training.ui.adapter.TrainingAdapter
import com.candra.ptpn_employee_training.ui.viewmodel.EmployeeViewModel
import com.candra.ptpn_employee_training.ui.viewmodel.TrainingViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DetailEmployee : AppCompatActivity(){

    private lateinit var binding: ActivityDetailEmployeeBinding
    private val employeeViewModel by viewModels<EmployeeViewModel>()
    private val trainingViewModel by viewModels<TrainingViewModel>()
    private val adapterTraining by lazy { TrainingAdapter(::onClickData) }
    private val isValid = mutableListOf(false,false,false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shrinkFab()
        setAllData()
        setAdapter()
    }


    private fun setAllData(){
        intent.extras?.getParcelable<employe>(EXTRA_EMPLOYEE)?.let { employee ->
            with(binding){
                edtName.setText(employee.name)
                edtYourNkr.setText(employee.nkr)
                edtJabatan.setText(employee.jabatan)
                edtUnitKebun.setText(employee.unitKebun)

                btnEdit.setOnClickListener {
                    changeData(employee.employeId)
                }

                addTrainingEmployee.setOnClickListener {
                    actionAddTraining(employee.nkr,employee.name)
                }

               setToolbar(employee.name)

                val nameEmployee = "%${employee.nkr}%"

                trainingViewModel.showAllTraining(nameEmployee).observe(this@DetailEmployee){ trainingList ->
                    showAllTraining(trainingList)
                }
            }
        }
    }

    private fun onClickData(data: training){
        Intent(this@DetailEmployee,DetailTraining::class.java).apply {
            putExtra(EXTRA_TRAINING,data)
        }.also { startActivity(it) }
    }

   private fun showAllTraining(it: List<training>){
       binding.trainingData.text = getString(R.string.training,"(${it.size})")
       adapterTraining.setTemptSubmitData(it)
   }

    private fun changeData(employeeId: Int){
        binding.apply {
           val name = tilInputName.editText?.text.toString()
            val nkr = tilInputNkr.editText?.text.toString()
            val jabatan = tilInputJabatan.editText?.text.toString()
            val unitKebun = tilInputUnitKebun.editText?.text.toString()

            if (name.isEmpty() || nkr.isEmpty() || jabatan.isEmpty() || unitKebun.isEmpty()){
                Toast.makeText(this@DetailEmployee,getString(R.string.attention_your_input),Toast.LENGTH_SHORT).show()
            }else{
                val employeeData = employe(employeeId,name,nkr,jabatan,unitKebun)
                onBackPressed()
                employeeViewModel.updateEmployee(employeeData)

            }
        }

    }

    private fun setToolbar(titleData: String){
        with(binding){
            toolbarDetailEmployee.apply {
                title = getString(R.string.detail_employee,titleData)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }
        }
    }

    private fun shrinkFab(){
        binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY < oldScrollY) binding.addTrainingEmployee.extend() else binding.addTrainingEmployee.shrink()
        })
    }

    private fun setAdapter(){
        binding.recyclerviewTraining.apply {
            layoutManager = LinearLayoutManager(this@DetailEmployee)
            adapter = adapterTraining
        }
    }

    private fun actionAddTraining(name: String,name2: String){
        val builderDialog = Dialog(this@DetailEmployee)
        val dialogBinding = AddTrainingDialogBinding.inflate(layoutInflater)

        builderDialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(dialogBinding.root)
        }



        with(dialogBinding){
            closeBtn.setOnClickListener {
                builderDialog.dismiss()
            }
            titleAdd.text = getString(R.string.add_employee,PELATIHAN)

            edtTextYourName.setText(name)

            setCalendar(edtStartTraining)

            setCalendar(edtEndTraining)

            edtNameTraining.doAfterTextChanged {
                val nameValid = it?.isNotBlank() ?: false
                isValid[0] = nameValid
                tilNameTraining.apply {
                    if (!nameValid) error = getString(R.string.name_training_not_be_empty)
                    isErrorEnabled = !nameValid
                }
                validateButton(btnConfirmation)
            }
            edtStartTraining.doAfterTextChanged {
                val startTrainingValid = it?.isNotBlank() ?: false
                isValid[1] = startTrainingValid
                tilStartTraining.apply {
                    if (!startTrainingValid) error = getString(R.string.start_training_not_be_empty)
                    isErrorEnabled = !startTrainingValid
                }
                validateButton(btnConfirmation)
            }
            edtEndTraining.doAfterTextChanged {
                val endTrainingValid = it?.isNotBlank() ?: false
                isValid[2] = endTrainingValid
                tillEndTraining.apply {
                    if (!endTrainingValid) error = getString(R.string.end_training_not_be_empty)
                    isErrorEnabled = !endTrainingValid
                }
                validateButton(btnConfirmation)
            }
            btnConfirmation.setOnClickListener {
                val nameEmployee = tilInputName.editText?.text.toString()
                val nameTraining = tilNameTraining.editText?.text.toString().lowercase().trim()
                val startTraining = tilStartTraining.editText?.text.toString()
                val endTraining = tillEndTraining.editText?.text.toString()
                val trainingData = training(0,name2,nameEmployee,nameTraining,startTraining,endTraining)
                trainingViewModel.insertTraining(trainingData)
                builderDialog.dismiss()
                Toast.makeText(this@DetailEmployee,getString(R.string.successfully_add_toast),Toast.LENGTH_SHORT).show()
            }
        }
        builderDialog.apply {
            show()
            window?.apply {
                setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                attributes.windowAnimations = R.style.DialogAnimation
                setGravity(Gravity.BOTTOM)
            }
        }
    }

    private fun validateButton(btnConfirmation: MaterialButton){
        btnConfirmation.isEnabled = isValid.filter { it }.size == 3
    }

    private fun setCalendar(edtStartDate: TextInputEditText){
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener{_,year,month,dayOfMonth ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            val dateFormat = DATE_FORMAT
            val simpleDateFormat = SimpleDateFormat(dateFormat,Locale.getDefault())
            edtStartDate.setText(simpleDateFormat.format(calendar.time))
        }
        edtStartDate.setOnClickListener {
            DatePickerDialog(this@DetailEmployee,datePicker,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}