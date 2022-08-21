package com.candra.ptpn_employee_training.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.candra.ptpn_employee_training.data.employe
import com.candra.ptpn_employee_training.data.training

@Database(entities = [employe::class,training::class], version = 1, exportSchema = false)
abstract class DatabasePtpn : RoomDatabase(){
    abstract fun employeeDao():  EmployeeDao
    abstract fun trainingDao(): TrainingDao
}