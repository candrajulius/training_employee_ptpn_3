package com.candra.ptpn_employee_training.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.candra.ptpn_employee_training.helper.Constant
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "training")
data class training(
    @PrimaryKey(autoGenerate = true)
    val trainingId: Int,

    @ColumnInfo(name = Constant.NAME_EMPLOYEE2)
    val nameEmployee2: String,

    @ColumnInfo(name = Constant.NKR)
    val nameEmployee: String,

    @ColumnInfo(name = Constant.NAME_TRAINING)
    val nameTraining: String,

    @ColumnInfo(name = Constant.START_DATE)
    val startDate: String,

    @ColumnInfo(name = Constant.END_DATE)
    val endDate: String
): Parcelable