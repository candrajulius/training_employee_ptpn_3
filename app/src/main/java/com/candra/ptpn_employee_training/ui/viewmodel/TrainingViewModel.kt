package com.candra.ptpn_employee_training.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.candra.ptpn_employee_training.data.training
import com.candra.ptpn_employee_training.usecase.EmployeeTrainingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val useCase: EmployeeTrainingUseCase
) : ViewModel()
{
    fun showAllTraining(nameEmployee: String) = useCase.showTraining(nameEmployee).asLiveData()

    fun insertTraining(training: training) = viewModelScope.launch {
        useCase.insertTraining(training)
    }

    fun updateTraining(training: training) = viewModelScope.launch {
        useCase.updateTraining(training)
    }

    fun deleteItemTraining(training: training) = viewModelScope.launch {
        useCase.deleteItemTraining(training)
    }

    fun deleteAllTraining(search: String) = viewModelScope.launch {
        useCase.deleteAllTraining(search)
    }

    fun deleteAllOFTraining() = viewModelScope.launch {
        useCase.deleteAllOfTraining()
    }

    fun showAllTraining() = useCase.showAllTraining().asLiveData()

    fun showAllNameEmployeeWhereNameTraining(nameTraining: String) = useCase.showAllNameEmployeeWhereNameTraining(nameTraining).asLiveData()


}