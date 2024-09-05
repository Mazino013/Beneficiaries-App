package com.example.beneficiaries.data.model

import android.os.Parcelable
import com.example.beneficiaries.util.Constants
import kotlinx.parcelize.Parcelize

/*Represents a beneficiary with fields (FN, LN)
& implement parcelable for data transfer between fragments*/

@Parcelize
data class Beneficiary(
    val firstName: String,
    val lastName: String,
    val beneType: String,
    val designationCode: String,
    val ssn: String,
    val dateOfBirth: String,
    val phoneNumber: String,
    val address: String
) : Parcelable {
    val designation: String
        get() = if (designationCode == Constants.DESIGNATION_PRIMARY)
            Constants.DESIGNATION_PRIMARY_DISPLAY
        else
            Constants.DESIGNATION_CONTINGENT_DISPLAY
}