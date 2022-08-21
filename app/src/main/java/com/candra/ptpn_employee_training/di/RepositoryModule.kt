package com.candra.ptpn_employee_training.di

import com.candra.ptpn_employee_training.repository.IRepository
import com.candra.ptpn_employee_training.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(repository: Repository):IRepository
}