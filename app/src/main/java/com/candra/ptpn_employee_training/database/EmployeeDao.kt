package com.candra.ptpn_employee_training.database

import androidx.room.*
import com.candra.ptpn_employee_training.data.employe
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    // menambahkan data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employe: employe)

    // Menghapus data satu persatu
    @Delete
    suspend fun deleteEmployee(employe: employe)

    // Mengupdate data
    @Update
    suspend fun updateDataEmployee(employe: employe)

    // Mengahapus data semuanya
    @Query("delete from employee")
    suspend fun deleteAll()

    // Mencari data di database berdasarkan name
    @Query("select * from employee where name_employee like :searchQuery or nkr like :searchQuery")
    fun searchEmployee(searchQuery: String): Flow<List<employe>>

    // Menampilkan data berdasarkan employeeId secara Ascending
    @Query("select * from employee order by employeId asc")
    fun showAllDataEmployee(): Flow<List<employe>>
}