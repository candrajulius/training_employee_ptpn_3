package com.candra.ptpn_employee_training.di

import com.candra.ptpn_employee_training.usecase.EmployeeTrainingInteract
import com.candra.ptpn_employee_training.usecase.EmployeeTrainingUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideEmployeeUseCase(employeeTrainingInteract: EmployeeTrainingInteract):EmployeeTrainingUseCase
}