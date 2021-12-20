package org.algosketch.inubus.data.repository

import android.content.Context
import org.algosketch.inubus.data.model.Memo
import org.algosketch.inubus.global.constant.LocalStorageKeys
import org.algosketch.inubus.global.util.MyApplication

class LocalRepository : Repository {
    override suspend fun getMemo(): Memo {
        val context = MyApplication.applicationContext()
        val sharedPref = context.getSharedPreferences(LocalStorageKeys.default, Context.MODE_PRIVATE)
        return Memo(sharedPref.getString("memo", "null"))
    }

    override suspend fun writeMemo(content: String) {
        val context = MyApplication.applicationContext()
        val sharedPref = context.getSharedPreferences(LocalStorageKeys.default, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("memo", content)
            commit()
        }
    }
}