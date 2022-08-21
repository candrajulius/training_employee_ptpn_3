package com.candra.ptpn_employee_training.ui.activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.ListAdapter
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.candra.ptpn_employee_training.R
import com.candra.ptpn_employee_training.databinding.ActivityMainBinding
import com.candra.ptpn_employee_training.databinding.AddDialogLayoutBinding
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            trainingCard.setOnClickListener {
               startActivity(Intent(this@MainActivity,TrainingActivity::class.java))
            }
            employeeCard.setOnClickListener {
                startActivity(Intent(this@MainActivity,EmployeeActivity::class.java))
            }
        }
    }

}