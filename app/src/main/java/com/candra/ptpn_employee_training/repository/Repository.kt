package com.candra.ptpn_employee_training.repository

import com.candra.ptpn_employee_training.LocalDataSource
import com.candra.ptpn_employee_training.data.employe
import com.candra.ptpn_employee_training.data.training
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource
) : IRepository
{
    override fun showAllEmployee(): Flow<List<employe>> = localDataSource.showAllEmployee()

    override suspend fun insertEmployee(employe: employe) = localDataSource.insertEmployee(employe)

    override suspend fun deleteItemEmployee(employe: employe) = localDataSource.deleteItemEmployee(employe)

    override suspend fun deleteAllEmployee() = localDataSource.deleteAllEmployee()

    override suspend fun updateDataEmployee(employe: employe) = localDataSource.updateDataEmployee(employe)

    override fun searchEmployee(searchQuery: String): Flow<List<employe>> = localDataSource.searchEmployee(searchQuery)

    override fun showTraining(nameEmployee: String): Flow<List<training>> = localDataSource.showTraining(nameEmployee)

    override suspend fun insertTraining(training: training) = localDataSource.insertTraining(training)

    override suspend fun deleteItemTraining(training: training) = localDataSource.deleteItemTraining(training)

    override suspend fun updateTraining(training: training) = localDataSource.updateTraining(training)

    override suspend fun deleteAllTraining(search: String) = localDataSource.deleteAllTraining(search)

    override suspend fun deleteAllOfTraining() = localDataSource.deleteAllOfTraining()

    override  fun showAllTraining(): Flow<List<training>> = localDataSource.showAllTraining()

    override  fun showAllNameEmployeeWhereNameTraining(nameTraining: String): Flow<List<training>> = localDataSource.showAllNameEmployeeWhereNameTraining(nameTraining)

}