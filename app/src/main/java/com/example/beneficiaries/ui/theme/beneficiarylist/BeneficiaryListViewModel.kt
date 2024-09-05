package com.example.beneficiaries.ui.theme.beneficiarylist

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.beneficiaries.data.model.Beneficiary
import com.example.beneficiaries.data.model.repository.BeneficiaryRepository

class BeneficiaryListViewModel(private val repository: BeneficiaryRepository) : ViewModel() {
    private val _beneficiaries = MutableLiveData<List<Beneficiary>>()
    val beneficiaries: LiveData<List<Beneficiary>> = _beneficiaries

    init {
        loadBeneficiaries()
    }

    /*Manages the List of Beneficiaries &
    * Loads Data From Repository & Exposes it Via LiveData*/


    private fun loadBeneficiaries() {
        try {
            val loadedBeneficiaries = repository.getBeneficiaries()
            _beneficiaries.value = loadedBeneficiaries
            Log.d("BeneficiaryListViewModel", "Loaded ${loadedBeneficiaries.size} beneficiaries")
        } catch (e: Exception) {
            Log.e("BeneficiaryListViewModel", "Error loading beneficiaries: ${e.message}", e)
            _beneficiaries.value = emptyList()
        }
    }
}

class BeneficiaryListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(BeneficiaryListViewModel::class.java) ->
                BeneficiaryListViewModel(BeneficiaryRepository(context)) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
}