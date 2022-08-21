package com.candra.ptpn_employee_training.usecase

import com.candra.ptpn_employee_training.data.employe
import com.candra.ptpn_employee_training.data.training
import kotlinx.coroutines.flow.Flow

interface EmployeeTrainingUseCase{
    fun showEmployee(): Flow<List<employe>>
    suspend fun insertEmployee(employe: employe)
    suspend fun deleteItemEmployee(employe: employe)
    suspend fun deleteAllEmployee()
    suspend fun updateDataEmployee(employe: employe)
    fun searchEmployee(searchQuery: String): Flow<List<employe>>


    fun showTraining(nameEmployee: String): Flow<List<training>>
    suspend fun insertTraining(training: training)
    suspend fun deleteItemTraining(training: training)
    suspend fun updateTraining(training: training)
    suspend fun deleteAllTraining(search: String)
    suspend fun deleteAllOfTraining()
    fun showAllTraining(): Flow<List<training>>
    fun showAllNameEmployeeWhereNameTraining(nameTraining: String): Flow<List<training>>
}