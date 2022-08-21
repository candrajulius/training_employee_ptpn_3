package com.candra.ptpn_employee_training.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.candra.ptpn_employee_training.data.training
import com.candra.ptpn_employee_training.databinding.ItemListTrainingBinding

class TrainingPtpnAdapter(
   private val onClickData: (training) -> Unit
): RecyclerView.Adapter<TrainingPtpnAdapter.ViewHolder>(){

    inner class ViewHolder(
        private val binding: ItemListTrainingBinding
    ): RecyclerView.ViewHolder(
        binding.root
    ){
        fun bind(dataTraining: training){
            with(binding){
                edtYourNameTraining.setText(dataTraining.nameTraining)
                root.setOnClickListener {
                    onClickData(dataTraining)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrainingPtpnAdapter.ViewHolder = ViewHolder(
        ItemListTrainingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: TrainingPtpnAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    val differ = AsyncListDiffer(this,object: DiffUtil.ItemCallback<training>(){
        override fun areItemsTheSame(oldItem: training, newItem: training): Boolean {
            return oldItem.trainingId == newItem.trainingId
        }

        override fun areContentsTheSame(oldItem: training, newItem: training): Boolean {
            return oldItem == newItem
        }
    })

    fun temptAllData(listData: List<training>){
        differ.submitList(listData)
    }

}