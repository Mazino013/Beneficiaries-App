package com.example.beneficiaries.ui.theme.beneficiarylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beneficiaries.R
import com.example.beneficiaries.data.model.Beneficiary
import com.example.beneficiaries.ui.theme.beneficiarydetail.BeneficiaryDetailFragment
import com.example.beneficiaries.util.Constants

class BeneficiaryListFragment : Fragment() {
    private lateinit var viewModel: BeneficiaryListViewModel
    private lateinit var adapter: BeneficiaryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Create RecyclerView programmatically
        val recyclerView = RecyclerView(requireContext()).apply {
            layoutManager = LinearLayoutManager(requireContext())
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        // Initialize ViewModel
        viewModel = ViewModelProvider(this, BeneficiaryListViewModelFactory(requireContext()))
            .get(BeneficiaryListViewModel::class.java)

        // Set up Adapter
        adapter = BeneficiaryAdapter { beneficiary ->
            navigateToDetail(beneficiary)
        }

        recyclerView.adapter = adapter

        // Observe LiveData
        viewModel.beneficiaries.observe(viewLifecycleOwner) { beneficiaries ->
            adapter.submitList(beneficiaries)
        }

        return recyclerView
    }

    private fun navigateToDetail(beneficiary: Beneficiary) {
        val detailFragment = BeneficiaryDetailFragment.newInstance(beneficiary)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment, Constants.TAG_BENEFICIARY_DETAIL)
            .addToBackStack(null)
            .commit()
    }
}