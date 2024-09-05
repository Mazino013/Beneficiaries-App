package com.example.beneficiaries.ui.theme.beneficiarylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.beneficiaries.data.model.Beneficiary

class BeneficiaryAdapter(private val onItemClick: (Beneficiary) -> Unit) :
    ListAdapter<Beneficiary, BeneficiaryAdapter.ViewHolder>(BeneficiaryDiffCallback()) {

    /*Display List in a RecyclerView*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beneficiary = getItem(position)
        holder.bind(beneficiary)
    }
    /*Binds Data to Each List Item & Handle Click Navigation to Details*/

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text1: TextView = itemView.findViewById(android.R.id.text1)
        private val text2: TextView = itemView.findViewById(android.R.id.text2)

        fun bind(beneficiary: Beneficiary) {
            text1.text = "${beneficiary.firstName} ${beneficiary.lastName}"
            text2.text = "${beneficiary.beneType} - ${beneficiary.designation}"
            itemView.setOnClickListener { onItemClick(beneficiary) }
        }
    }

    class BeneficiaryDiffCallback : DiffUtil.ItemCallback<Beneficiary>() {
        override fun areItemsTheSame(oldItem: Beneficiary, newItem: Beneficiary): Boolean {
            return oldItem.ssn == newItem.ssn
        }

        override fun areContentsTheSame(oldItem: Beneficiary, newItem: Beneficiary): Boolean {
            return oldItem == newItem
        }
    }
}