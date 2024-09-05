package com.example.beneficiaries.util

import android.content.Context
import android.util.Log
import com.example.beneficiaries.data.model.Beneficiary
import org.json.JSONArray
import org.json.JSONException

object JsonParser {
    fun parseBeneficiaries(context: Context): List<Beneficiary> {
        val beneficiaries = mutableListOf<Beneficiary>()
        try {
            val inputStream = context.resources.openRawResource(
                context.resources.getIdentifier(Constants.JSON_FILE_NAME, "raw", context.packageName)
            )
            /*Reads Entire Content of Input Stream (JSON FILE)*/
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(jsonString)

            /*Iterate Through Each Object In JSON Array & Create Formatted Address*/
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val addressObject = jsonObject.getJSONObject("beneficiaryAddress")
                val addressString = listOf(
                    addressObject.getString("firstLineMailing"),
                    addressObject.getString("city"),
                    "${addressObject.getString("stateCode")} ${addressObject.getString("zipCode")}",
                    addressObject.getString("country")
                )
                val address = addressString.joinToString(", ")

                val beneficiary = Beneficiary(
                    firstName = jsonObject.getString("firstName"),
                    lastName = jsonObject.getString("lastName"),
                    beneType = jsonObject.getString("beneType"),
                    designationCode = jsonObject.getString("designationCode"),
                    ssn = jsonObject.getString("socialSecurityNumber"),
                    dateOfBirth = jsonObject.getString("dateOfBirth"),
                    phoneNumber = jsonObject.getString("phoneNumber"),
                    address = address
                )
                beneficiaries.add(beneficiary)
                Log.d("JsonParser", "Parsed beneficiary: $beneficiary")
            }
            Log.d("JsonParser", "Total beneficiaries parsed: ${beneficiaries.size}")
        } catch (e: JSONException) {
            Log.e("JsonParser", "Error parsing JSON: ${e.message}", e)
        } catch (e: Exception) {
            Log.e("JsonParser", "Error reading or parsing file: ${e.message}", e)
        }
        return beneficiaries
    }
}