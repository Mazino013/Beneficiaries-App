package com.example.beneficiaries.ui.theme.beneficiarylist

import androidx.recyclerview.widget.DiffUtil
import com.example.beneficiaries.data.model.Beneficiary

class BeneficiaryDiffCallback : DiffUtil.ItemCallback<Beneficiary>() {
    override fun areItemsTheSame(oldItem: Beneficiary, newItem: Beneficiary): Boolean {
        // Compare items based on a unique identifier, such as SSN
        return oldItem.ssn == newItem.ssn
    }

    override fun areContentsTheSame(oldItem: Beneficiary, newItem: Beneficiary): Boolean {
        // Compare the entire content of the items
        return oldItem == newItem
    }
}