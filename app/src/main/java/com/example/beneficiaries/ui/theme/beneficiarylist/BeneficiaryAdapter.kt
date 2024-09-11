package com.example.beneficiaries.ui.theme.beneficiarylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.beneficiaries.data.model.Beneficiary
import com.example.beneficiaries.util.Constants

class BeneficiaryAdapter(private val onItemClick: (Beneficiary) -> Unit) :
    ListAdapter<Beneficiary, BeneficiaryAdapter.ViewHolder>(BeneficiaryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a LinearLayout to hold the item views
        val layout = LinearLayout(parent.context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(
                Constants.PADDING_MEDIUM,
                Constants.PADDING_MEDIUM,
                Constants.PADDING_MEDIUM,
                Constants.PADDING_MEDIUM
            )
        }

        // Create TextViews for displaying beneficiary details
        val text1 = TextView(parent.context).apply {
            id = View.generateViewId()
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val text2 = TextView(parent.context).apply {
            id = View.generateViewId()
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        // Add TextViews to the layout
        layout.addView(text1)
        layout.addView(text2)

        return ViewHolder(layout, text1, text2)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beneficiary = getItem(position)
        holder.bind(beneficiary)
    }

    inner class ViewHolder(itemView: View, private val text1: TextView, private val text2: TextView) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(beneficiary: Beneficiary) {
            text1.text = "${beneficiary.firstName} ${beneficiary.lastName}"
            text2.text = "${beneficiary.beneType} - ${beneficiary.designation}"
            itemView.setOnClickListener { onItemClick(beneficiary) }
        }
    }
}