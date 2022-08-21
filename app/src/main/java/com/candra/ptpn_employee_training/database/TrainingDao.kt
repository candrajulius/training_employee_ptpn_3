package com.candra.ptpn_employee_training.database

import androidx.room.*
import com.candra.ptpn_employee_training.data.training
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTraining(training: training)

    @Update
    suspend fun updateTraining(training: training)

    @Delete
    suspend fun deleteItemTraining(training: training)

    @Query("select * from training where nkr like :nameEmployee order by trainingId asc")
    fun showAllDataTraining(nameEmployee: String): Flow<List<training>>

    @Query("delete from training where nkr like :nameEmployee")
    suspend fun deleteAllTrainingWhereNameEmployee(nameEmployee: String)

    @Query("delete from training")
    suspend fun deleteAllTraining()

    @Query("select * from training group by name_training,start_date")
    fun showAllTraining(): Flow<List<training>>

    @Query("select * from training where name_training like :nameTraining")
    fun showAllNameEmployeeWhereNameTraining(nameTraining: String): Flow<List<training>>

}