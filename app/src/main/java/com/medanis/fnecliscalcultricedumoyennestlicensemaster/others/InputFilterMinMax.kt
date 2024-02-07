package com.medanis.fnecliscalcultricedumoyennestlicensemaster.others

import android.content.Context
import android.text.InputFilter
import android.text.Spanned
import com.google.gson.Gson

fun saveHistoryArray(context: Context, key: String, array: Array<String>) {
    val sharedPreferences = context.getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val gson = Gson()
    val json = gson.toJson(array)
    editor.putString(key, json)
    editor.apply()
}

fun getHistoryArray(context: Context, key: String): Array<String>? {
    val sharedPreferences = context.getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = sharedPreferences.getString(key, null)
    return gson.fromJson(json, Array<String>::class.java)
}
class InputFilterMinMax(min:Float, max:Float): InputFilter {
    private var min:Float = 0.toFloat()
    private var max:Float = 0.toFloat()
    init{
        this.min = min
        this.max = max
    }
    override fun filter(source:CharSequence, start:Int, end:Int, dest: Spanned, dstart:Int, dend:Int): CharSequence? {
        try
        {
            val input = (dest.subSequence(0, dstart).toString() + source + dest.subSequence(dend, dest.length)).toFloat()
            if (isInRange(min, max, input))
                return null
        }
        catch (nfe:NumberFormatException) {}
        return ""
    }
    fun isInRange(a:Float, b:Float, c:Float):Boolean {
        return if (b > a) c in a..b else c in b..a
    }
}