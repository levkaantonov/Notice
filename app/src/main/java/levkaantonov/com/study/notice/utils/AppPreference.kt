package levkaantonov.com.study.notice.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreference {
    private const val INIT_USER = "initUser"
    private const val TYPE_DB = "typeDb"
    private const val NAME_PREF = "preference"

    private lateinit var mPreferences: SharedPreferences

    fun getPreference(ctx: Context) : SharedPreferences{
        mPreferences = ctx.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
        return mPreferences
    }

    fun setInitUser(init: Boolean){
        mPreferences.edit()
            .putBoolean(INIT_USER, init)
            .apply()
    }

    fun setDbType(type: String){
        mPreferences.edit()
            .putString(TYPE_DB, type)
            .apply()
    }

    fun getInitUser():Boolean{
        return mPreferences.getBoolean(INIT_USER, false)
    }

    fun getDbType():String{
        return mPreferences.getString(TYPE_DB, TYPE_ROOM).toString()
    }
}