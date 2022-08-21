package com.candra.ptpn_employee_training.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.candra.ptpn_employee_training.R
import com.candra.ptpn_employee_training.data.training
import com.candra.ptpn_employee_training.databinding.DetailLayoutTrainingEmployeeBinding
import com.candra.ptpn_employee_training.helper.Constant
import com.candra.ptpn_employee_training.helper.Constant.DETAIL
import com.candra.ptpn_employee_training.helper.Constant.EXTRA_TRAINING
import com.candra.ptpn_employee_training.ui.viewmodel.TrainingViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DetailTraining: AppCompatActivity(){

    private lateinit var binding: DetailLayoutTrainingEmployeeBinding
    private val trainingViewModel by viewModels<TrainingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailLayoutTrainingEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        receiveDataFromDetailEmployee()
    }

    private fun setToolbar(nameTittle: String){
        binding.toolbarDetailTraining.apply {
            title = "$DETAIL $nameTittle"
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun receiveDataFromDetailEmployee(){
        with(binding){
            intent.extras?.getParcelable<training>(EXTRA_TRAINING)?.let { data ->

                edtNameEmployee.setText(data.nameEmployee)
                edtNameTraining.setText(data.nameTraining)
                edtStartTraining.setText(data.startDate)
                edtEndTraining.setText(data.endDate)
                edtTextYourName.setText(data.nameEmployee2)

                setCalendar(edtStartTraining)

                setCalendar(edtEndTraining)

                setToolbar(data.nameTraining)

                btnEdit.setOnClickListener {
                    changeData(data.trainingId)
                }
                btnDelete.setOnClickListener {
                    showDialogQuestion(data)
                }
            }
        }
    }

    private fun changeData(trainingId: Int){
        binding.apply {
            val nameEmployee = tilInputName.editText?.text.toString()
            val nameTraining = tilNameTraining.editText?.text.toString()
            val startTraining = tilStartTraining.editText?.text.toString()
            val endTraining = tillEndTraining.editText?.text.toString()
            val nameEmployee2 = tilInputName2.editText?.text.toString()

            if (nameEmployee.isEmpty() || nameTraining.isEmpty() || startTraining.isEmpty() || endTraining.isEmpty())
            {
                Toast.makeText(this@DetailTraining,getString(R.string.attention_your_input),Toast.LENGTH_SHORT).show()
            }else{

                val inputanData = training(trainingId,nameEmployee2,nameEmployee,nameTraining,startTraining,endTraining)

                trainingViewModel.updateTraining(inputanData).also { onBackPressed() }
            }
        }

    }

    private fun showDialogQuestion(data: training){
        val builder = AlertDialog.Builder(this@DetailTraining)
        builder.apply {
            setPositiveButton(getString(R.string.yes)){_,_ ->
                trainingViewModel.deleteItemTraining(data)
                onBackPressed()
                Toast.makeText(this@DetailTraining,getString(R.string.succesfully_delete_toast,data.nameTraining),Toast.LENGTH_SHORT).show()
            }
            setNegativeButton(getString(R.string.no)){_,_ -> }
            setTitle(getString(R.string.title_dialog,data.nameTraining))
            setMessage(getString(R.string.message_dialog,data.nameTraining))
            create().show()
        }
    }

    private fun setCalendar(edtStartDate: TextInputEditText){
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener{ _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            val dateFormat = Constant.DATE_FORMAT
            val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
            edtStartDate.setText(simpleDateFormat.format(calendar.time))
        }
        edtStartDate.setOnClickListener {
            DatePickerDialog(this@DetailTraining,datePicker,calendar.get(Calendar.YEAR),calendar.get(
                Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

}