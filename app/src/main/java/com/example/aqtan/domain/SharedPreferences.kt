package com.example.aqtan.domain



import android.content.Context
import android.content.SharedPreferences
import com.example.aqtan.data.Constants

class SharedPreferences(context: Context) {
    private  var sharedPreferences: SharedPreferences
    private var editSharedPreferences: SharedPreferences.Editor



    init {
        sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        editSharedPreferences = sharedPreferences.edit()

    }
    fun setSharedPreferences(name:String, value:String): Boolean{
        return try {
            editSharedPreferences.putString(name,value)
            editSharedPreferences.commit()
            true
        }catch (e:Exception){
            false
        }
    }
    fun setBooleanSharedPreferences(name:String, value:Boolean): Boolean{
        return try {
            editSharedPreferences.putBoolean(name,value)
            editSharedPreferences.commit()
            true
        }catch (e:Exception){
            false
        }
    }
    fun getSharedPreferences(name:String, defaultValue:String): String{
        return sharedPreferences.getString(name, defaultValue)!!
    }

    fun getBooleanSharedPreferences(name:String, defaultValue:Boolean): Boolean{
        return sharedPreferences.getBoolean(name, defaultValue)
    }
}