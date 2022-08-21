package com.candra.ptpn_employee_training

import com.candra.ptpn_employee_training.data.employe
import com.candra.ptpn_employee_training.data.training
import com.candra.ptpn_employee_training.database.EmployeeDao
import com.candra.ptpn_employee_training.database.TrainingDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val employeeDao: EmployeeDao,
    private val trainingDao: TrainingDao
)
{
    //------------------- Implementation Employee -----------------//
    fun showAllEmployee(): Flow<List<employe>> = employeeDao.showAllDataEmployee()
    suspend fun insertEmployee(employe: employe) = employeeDao.insertEmployee(employe)
    suspend fun deleteItemEmployee(employe: employe) = employeeDao.deleteEmployee(employe)
    suspend fun deleteAllEmployee() = employeeDao.deleteAll()
    suspend fun updateDataEmployee(employe: employe) = employeeDao.updateDataEmployee(employe)
    fun searchEmployee(searchQuery: String) = employeeDao.searchEmployee(searchQuery)
    //------------------- Implementation Employee -----------------//


    //------------------- Implementation Training -----------------//
    fun showTraining(nameEmployee: String) = trainingDao.showAllDataTraining(nameEmployee)
   suspend fun insertTraining(training: training) = trainingDao.insertTraining(training)
   suspend fun deleteItemTraining(training: training) = trainingDao.deleteItemTraining(training)
   suspend fun updateTraining(training: training) = trainingDao.updateTraining(training)
   suspend fun deleteAllTraining(search: String) = trainingDao.deleteAllTrainingWhereNameEmployee(search)
   suspend fun deleteAllOfTraining() = trainingDao.deleteAllTraining()
   fun showAllTraining(): Flow<List<training>> = trainingDao.showAllTraining()
   fun showAllNameEmployeeWhereNameTraining(nameTraining: String): Flow<List<training>> = trainingDao.showAllNameEmployeeWhereNameTraining(nameTraining)

    //------------------- Implementation Training -----------------//




}