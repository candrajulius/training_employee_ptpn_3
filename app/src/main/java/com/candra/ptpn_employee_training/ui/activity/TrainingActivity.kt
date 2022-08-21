package com.candra.ptpn_employee_training.ui.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.candra.ptpn_employee_training.R
import com.candra.ptpn_employee_training.data.training
import com.candra.ptpn_employee_training.databinding.ActivityLayoutTrainingBinding
import com.candra.ptpn_employee_training.databinding.BottomSheetDialogListEmployeeBinding
import com.candra.ptpn_employee_training.ui.adapter.ItemEmployeeJoinTrainingAdapter
import com.candra.ptpn_employee_training.ui.adapter.TrainingPtpnAdapter
import com.candra.ptpn_employee_training.ui.viewmodel.TrainingViewModel
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingActivity: AppCompatActivity()
{
    private lateinit var binding: ActivityLayoutTrainingBinding
    private val adapterTraining by lazy { TrainingPtpnAdapter(::onClickData ) }
    private val trainingViewModel by viewModels<TrainingViewModel>()
    private val adapterEmployeeJoinTraining by lazy { ItemEmployeeJoinTrainingAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        observerAllData()
    }

    private fun setToolbar(totalSizeTraining: String){
        binding.toolbarDetailActivityTraining.apply {
            title = "${getString(R.string.name_detail_training)} ($totalSizeTraining)"
            setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun setAdapter(){
        binding.rvTrainingLayout.apply {
            layoutManager = LinearLayoutManager(this@TrainingActivity)
            adapter = adapterTraining
        }
    }

    private fun observerAllData(){
        trainingViewModel.showAllTraining().observe(this,this::setDataAll)
    }

    private fun setDataAll(it: List<training>){
        setToolbar(it.size.toString())
        if (it.isEmpty()){
            showEmptyText(true)
        }else{
            showEmptyText(false)
            adapterTraining.temptAllData(it)
        }
    }

    private fun showEmptyText(isShow: Boolean){
        binding.apply {
            rvTrainingLayout.visibility = if(isShow) View.GONE else View.VISIBLE
            mtvNothingToShow.visibility = if (isShow) View.VISIBLE else View.GONE
        }
    }

    //TODO: Implementation Code Click Data To BottomSheetDialog
    private fun onClickData(dataTraining: training){
        val nameTraining = "%${dataTraining.nameTraining}%"
        showBottomSheetDialog(nameTraining)
    }

    private fun showBottomSheetDialog(nameTraining: String){
        val dialog = Dialog(this@TrainingActivity)
        val dialogBinding = BottomSheetDialogListEmployeeBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(dialogBinding.root)
        }
        with(dialogBinding){
            closeBtn.setOnClickListener { dialog.dismiss() }

            showEmployeeJoinTraining(nameTraining,titleTrainingEmployee)

            rvEmployeeJoinTraining.apply {
                layoutManager = LinearLayoutManager(this@TrainingActivity)
                adapter = adapterEmployeeJoinTraining
            }
        }

        dialog.apply {
            show()
            window?.apply {
                setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                attributes.windowAnimations = R.style.DialogAnimation
                setGravity(Gravity.BOTTOM)
            }
        }
    }

    private fun showEmployeeJoinTraining(nameTraining: String,mtvListTraining: MaterialTextView){
        trainingViewModel.showAllNameEmployeeWhereNameTraining(nameTraining).observe(this){
            setAllDataEmployeeJoinTraining(it,mtvListTraining)
        }
    }

    private fun setAllDataEmployeeJoinTraining(it: List<training>,mtvListTraining: MaterialTextView){
        mtvListTraining.text = getString(R.string.training_employee_his_join,"(${it.size})")
        adapterEmployeeJoinTraining.submitListData(it)
    }
}