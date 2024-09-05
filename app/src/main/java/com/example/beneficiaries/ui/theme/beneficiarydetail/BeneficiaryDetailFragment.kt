package com.example.beneficiaries.ui.theme.beneficiarydetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.beneficiaries.data.model.Beneficiary
import com.example.beneficiaries.util.Constants
import com.example.beneficiaries.util.Constants.MARGIN_MEDIUM
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class BeneficiaryDetailFragment : Fragment() {
    private lateinit var viewModel: BeneficiaryDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val context = requireContext()

        val scrollView = ScrollView(context)
        val linearLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setPadding(Constants.PADDING_MEDIUM,
                Constants.PADDING_MEDIUM,
                Constants.PADDING_MEDIUM,
                Constants.PADDING_MEDIUM)
        }
        scrollView.addView(linearLayout)

        viewModel = ViewModelProvider(this).get(BeneficiaryDetailViewModel::class.java)

        arguments?.getParcelable<Beneficiary>(Constants.KEY_BENEFICIARY)?.let {
            viewModel.setBeneficiary(it)
        }

        viewModel.beneficiary.observe(viewLifecycleOwner) { beneficiary ->
            linearLayout.removeAllViews()
            addDetailView(linearLayout, "Name", "${beneficiary.firstName} ${beneficiary.lastName}")
            addDetailView(linearLayout, "SSN", beneficiary.ssn)
            addDetailView(linearLayout, "Date of Birth", formatDateOfBirth(beneficiary.dateOfBirth))
            addDetailView(linearLayout, "Phone", beneficiary.phoneNumber)
            addDetailView(linearLayout, "Address", beneficiary.address)
            addDetailView(linearLayout, "Type", beneficiary.beneType)
            addDetailView(linearLayout, "Designation", beneficiary.designation)
        }

        return scrollView
    }

    private fun addDetailView(layout: LinearLayout, label: String, value: String) {
        val textView = TextView(requireContext()).apply {
            text = "$label: $value"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, MARGIN_MEDIUM) // Add margin between items
            }
        }
        layout.addView(textView)
    }

    private fun formatDateOfBirth(dateOfBirth: String): String {
        val inputFormat = SimpleDateFormat(Constants.DATE_FORMAT, Locale.US)
        val outputFormat = SimpleDateFormat(Constants.DATE_FORMAT, Locale.US)
        return try {
            val date = inputFormat.parse(dateOfBirth)
            outputFormat.format(date ?: return dateOfBirth)
        } catch (e: ParseException) {
            Log.e("BeneficiaryDetailFragment", "Error parsing date: ${e.message}")
            dateOfBirth
        }
    }

    companion object {
        fun newInstance(beneficiary: Beneficiary): BeneficiaryDetailFragment {
            val fragment = BeneficiaryDetailFragment()
            val args = Bundle()
            args.putParcelable(Constants.KEY_BENEFICIARY, beneficiary)
            fragment.arguments = args
            return fragment
        }
    }
}