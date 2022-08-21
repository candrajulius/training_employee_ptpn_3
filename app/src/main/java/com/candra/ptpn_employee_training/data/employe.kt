package com.candra.ptpn_employee_training.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.candra.ptpn_employee_training.helper.Constant
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "employee")
data class employe(
    @PrimaryKey(autoGenerate = true)
    val employeId: Int,

    @ColumnInfo(name = Constant.NAME_EMPLOYEE)
    val name: String,

    @ColumnInfo(name = Constant.NKR)
    val nkr: String,

    @ColumnInfo(name = Constant.JABATAN)
    val jabatan: String,

    @ColumnInfo(name = Constant.UNI_KEBUN)
    val unitKebun: String,
): Parcelable