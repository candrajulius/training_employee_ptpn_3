package com.candra.ptpn_employee_training.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.candra.ptpn_employee_training.data.training
import com.candra.ptpn_employee_training.databinding.ItemTrainingCardBinding

class TrainingAdapter(
   private val onClickData: (training) -> Unit
): RecyclerView.Adapter<TrainingAdapter.ViewHolder>(){

    inner class ViewHolder(
        private val binding: ItemTrainingCardBinding
    ): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(data: training){
            with(binding){
                tilTraining.editText?.setText(data.nameEmployee)
                tilNameTraining.editText?.setText(data.nameTraining)
                tilStartTraining.editText?.setText(data.startDate)
                tillEndTraining.editText?.setText(data.endDate)
//                edtNameTraining.setText(data.nameTraining)
//                edtStartTraining.setText(data.startDate)
//                edtEndTraining.setText(data.endDate)

                root.setOnClickListener {
                    onClickData(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingAdapter.ViewHolder {
        return ViewHolder(
            ItemTrainingCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: TrainingAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    val differ = AsyncListDiffer(this,object: DiffUtil.ItemCallback<training>(){
        override fun areItemsTheSame(oldItem: training, newItem: training): Boolean {
            return oldItem.trainingId == newItem.trainingId
        }

        override fun areContentsTheSame(oldItem: training, newItem: training): Boolean {
            return oldItem.trainingId == newItem.trainingId
        }

    })
    override fun getItemCount(): Int = differ.currentList.size

    fun setTemptSubmitData(it: List<training>){
        differ.submitList(it)
    }
}