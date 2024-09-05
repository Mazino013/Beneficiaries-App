package com.example.beneficiaries.ui.theme.beneficiarydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beneficiaries.data.model.Beneficiary

class BeneficiaryDetailViewModel : ViewModel() {
    private val _beneficiary = MutableLiveData<Beneficiary>()
    val beneficiary: LiveData<Beneficiary> = _beneficiary

    fun setBeneficiary(beneficiary: Beneficiary) {
        _beneficiary.value = beneficiary
    }
}