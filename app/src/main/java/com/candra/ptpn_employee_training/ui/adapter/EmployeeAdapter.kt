package com.candra.ptpn_employee_training.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.candra.ptpn_employee_training.data.employe
import com.candra.ptpn_employee_training.databinding.ItemEmployeeBinding

class EmployeeAdapter(
    private val onClick: (employe) -> Unit,
    private val onDelete: (employe) -> Unit
): RecyclerView.Adapter<EmployeeAdapter.ViewHolder>(){

    inner class ViewHolder(private val binding: ItemEmployeeBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(data: employe){
            with(binding){
                nameEmployee.text = data.name
                nkrEmployee.text = data.nkr
                jabatanEmployee.text = data.jabatan
                unitKebunEmployee.text = data.unitKebun
                root.setOnClickListener {
                    onClick(data)
                }
                btnDelete.setOnClickListener {
                    onDelete(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeAdapter.ViewHolder {
        return ViewHolder(
            ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: EmployeeAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    val differ = AsyncListDiffer(this,object: DiffUtil.ItemCallback<employe>(){
        override fun areItemsTheSame(oldItem: employe, newItem: employe): Boolean {
            return oldItem.employeId == newItem.employeId
        }

        override fun areContentsTheSame(oldItem: employe, newItem: employe): Boolean {
            return oldItem == newItem
        }
    })

    fun temptAllData(listData: List<employe>){
        differ.submitList(listData)
    }

}