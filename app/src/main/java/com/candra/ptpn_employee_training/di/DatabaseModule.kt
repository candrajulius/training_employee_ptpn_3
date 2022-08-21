package com.candra.ptpn_employee_training.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.candra.ptpn_employee_training.database.DatabasePtpn
import com.candra.ptpn_employee_training.database.EmployeeDao
import com.candra.ptpn_employee_training.database.TrainingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): DatabasePtpn =
         Room.databaseBuilder(
            context,
            DatabasePtpn::class.java,"ptpn_database.db"
        ).fallbackToDestructiveMigration()
             .allowMainThreadQueries().build()

    @Provides
    fun provideTrainingDao(database: DatabasePtpn): TrainingDao = database.trainingDao()

    @Provides
    fun provideEmployeeDao(database: DatabasePtpn): EmployeeDao = database.employeeDao()

}