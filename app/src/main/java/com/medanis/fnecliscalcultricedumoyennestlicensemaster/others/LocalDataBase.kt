package com.medanis.fnecliscalcultricedumoyennestlicensemaster.others

import android.content.Context
import android.util.Log
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.medanis.fnecliscalcultricedumoyennestlicensemaster.models.MODULE
import java.lang.reflect.Type


// Function to convert MutableList<MODULE> to JSON string
fun convertModulesToJson(modules: MutableList<MODULE>): String {
    val gson = Gson()
    return gson.toJson(modules)
}

// Function to save MutableList<MODULE> in SharedPreferences
fun saveModulesInSharedPreferences(
    mContext: Context,
    mData: MutableList<MODULE>,
    LEVEL_YEAR_SEMSTER: String?,
    speciality: String?,
    dateTime: String?
) {
    val historyArray = getHistoryArray(mContext, "history_array") ?: emptyArray()

    var tSpeciality = speciality
    if (speciality == "null") {
        tSpeciality = ""
    }

    val newValue = "$LEVEL_YEAR_SEMSTER&&$tSpeciality&&$dateTime"

    Log.e("historyArray", historyArray.contains(newValue).toString())

    if (!historyArray.contains(newValue)) {
        val updatedArray = historyArray.toMutableList().apply { add(newValue) }.toTypedArray()

        saveHistoryArray(mContext, "history_array", updatedArray)
    }
    val jsonString = convertModulesToJson(mData)
    val sharedPreferences = mContext.getSharedPreferences(
        "MODULE_PREFS_$LEVEL_YEAR_SEMSTER&&$tSpeciality&&$dateTime",
        Context.MODE_PRIVATE
    )
    val editor = sharedPreferences.edit()
    editor.putString("$LEVEL_YEAR_SEMSTER&&$tSpeciality&&$dateTime", jsonString)
    editor.apply()

}

//Function to retrieve MutableList<MODULE> from SharedPreferences
fun getModulesFromSharedPreferences(
    context: Context,
    LEVEL_YEAR_SEMSTER: String?,
    speciality: String?,
    dateTime: String?
): MutableList<MODULE>? {
    val sharedPreferences = context.getSharedPreferences(
        "MODULE_PREFS_$LEVEL_YEAR_SEMSTER&&$speciality&&$dateTime",
        Context.MODE_PRIVATE
    )
    val jsonString =
        sharedPreferences.getString("$LEVEL_YEAR_SEMSTER&&$speciality&&$dateTime", null)
    jsonString?.let {
        return convertJsonToModules(it)
    }
    return null
}

//ion to convert JSON string to MutableList<MODULE>
fun convertJsonToModules(jsonString: String): MutableList<MODULE> {
    val type: Type = object : TypeToken<MutableList<MODULE>>() {}.type
    val gson = Gson()
    return gson.fromJson(jsonString, type)
}