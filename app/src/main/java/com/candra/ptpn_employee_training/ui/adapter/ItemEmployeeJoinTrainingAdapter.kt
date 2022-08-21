package com.candra.ptpn_employee_training.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.candra.ptpn_employee_training.data.training
import com.candra.ptpn_employee_training.databinding.ItenEmployeeJoinTrainingBinding

class ItemEmployeeJoinTrainingAdapter : RecyclerView.Adapter<ItemEmployeeJoinTrainingAdapter.ViewHolder>()
{

    inner class ViewHolder(
        private val binding: ItenEmployeeJoinTrainingBinding
    ): RecyclerView.ViewHolder(
        binding.root
    )
    {
        fun bind(data: training){
            with(binding){
                edtNameEmployee.setText(data.nameEmployee2)
                edtYourNkr.setText(data.nameEmployee)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemEmployeeJoinTrainingAdapter.ViewHolder = ViewHolder(
        ItenEmployeeJoinTrainingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(
        holder: ItemEmployeeJoinTrainingAdapter.ViewHolder,
        position: Int
    ) {
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

    fun submitListData(it: List<training>){
        differ.submitList(it)
    }

}