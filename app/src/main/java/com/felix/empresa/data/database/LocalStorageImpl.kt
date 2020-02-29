package com.felix.empresa.data.database

import android.content.Context
import android.content.SharedPreferences
import com.felix.empresa.R

interface LocalStorage {
    fun save(key: String, value: String)
    fun clearAll()
    fun load(key: String, default: String): String
}

class LocalStorageImpl(
    context: Context
) : LocalStorage {

    var sharedPreferences: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preference_file_key),
        Context.MODE_PRIVATE
    )

    override fun clearAll() {
        with(sharedPreferences.edit()) {
            clear()
            commit()
        }
    }

    override fun load(key: String, default: String): String {
        return sharedPreferences.getString(key, default) ?: ""
    }

    override fun save(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            commit()
        }
    }

}