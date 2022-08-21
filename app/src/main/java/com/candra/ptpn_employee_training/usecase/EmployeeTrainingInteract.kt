package com.candra.ptpn_employee_training.usecase

import com.candra.ptpn_employee_training.data.employe
import com.candra.ptpn_employee_training.data.training
import com.candra.ptpn_employee_training.repository.IRepository
import com.candra.ptpn_employee_training.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmployeeTrainingInteract @Inject constructor(
    private val repository: IRepository
): EmployeeTrainingUseCase {
    override fun showEmployee(): Flow<List<employe>> =
        repository.showAllEmployee()

    override suspend fun insertEmployee(employe: employe) =
        repository.insertEmployee(employe)


    override suspend fun deleteItemEmployee(employe: employe) =
        repository.deleteItemEmployee(employe)

    override suspend fun deleteAllEmployee() =
        repository.deleteAllEmployee()

    override suspend fun updateDataEmployee(employe: employe) =
        repository.updateDataEmployee(employe)

    override fun searchEmployee(searchQuery: String): Flow<List<employe>> = repository.searchEmployee(searchQuery)


    override fun showTraining(nameEmployee: String): Flow<List<training>> = repository.showTraining(nameEmployee)

    override suspend fun insertTraining(training: training) = repository.insertTraining(training)

    override suspend fun deleteItemTraining(training: training) = repository.deleteItemTraining(training)

    override suspend fun updateTraining(training: training) = repository.updateTraining(training)

    override suspend fun deleteAllTraining(search: String) = repository.deleteAllTraining(search)

    override suspend fun deleteAllOfTraining() = repository.deleteAllOfTraining()

    override  fun showAllTraining(): Flow<List<training>> = repository.showAllTraining()

    override  fun showAllNameEmployeeWhereNameTraining(nameTraining: String): Flow<List<training>> =
        repository.showAllNameEmployeeWhereNameTraining(nameTraining)
}