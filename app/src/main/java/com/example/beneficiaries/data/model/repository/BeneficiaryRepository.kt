package com.example.beneficiaries.data.model.repository

import android.content.Context
import android.util.Log
import com.example.beneficiaries.data.model.Beneficiary
import com.example.beneficiaries.util.JsonParser

class BeneficiaryRepository(private val context: Context) {

    /*Loads Beneficiary Data From JSON File using JsonParser
    * Provides a Lis of Beneficiary Objects to ViewModel*/

    fun getBeneficiaries(): List<Beneficiary> {
        val beneficiaries = JsonParser.parseBeneficiaries(context)
        Log.d("BeneficiaryRepository", "Beneficiaries count: ${beneficiaries.size}")
        return beneficiaries
    }
}