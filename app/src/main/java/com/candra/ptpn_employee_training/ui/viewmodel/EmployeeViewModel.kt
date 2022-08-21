package com.candra.ptpn_employee_training.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.candra.ptpn_employee_training.data.employe
import com.candra.ptpn_employee_training.usecase.EmployeeTrainingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val useCase: EmployeeTrainingUseCase
): ViewModel()
{
    val showAllEmployee = useCase.showEmployee().asLiveData()

    fun insertEmployee(employe: employe) = viewModelScope.launch {
        useCase.insertEmployee(employe)
    }

    fun deleteItemEmployee(employe: employe) = viewModelScope.launch {
        useCase.deleteItemEmployee(employe)
    }

    fun deleteAllEmployee() = viewModelScope.launch {
        useCase.deleteAllEmployee()
    }

    fun updateEmployee(employe: employe) = viewModelScope.launch {
        useCase.updateDataEmployee(employe)
    }

    fun searchEmployee(searchQuery: String) = useCase.searchEmployee(searchQuery).asLiveData()

}