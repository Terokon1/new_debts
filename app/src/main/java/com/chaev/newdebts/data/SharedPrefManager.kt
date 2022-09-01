package com.chaev.newdebts.data

import android.content.Context
import android.content.SharedPreferences
import com.chaev.newdebts.R
import com.chaev.newdebts.domain.models.base.UserInfo
import com.chaev.newdebts.utils.Resources

class SharedPrefManager(context: Context) {
    private val prefs =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun loadToken(): String {
        return prefs.getString(Resources.REFRESH_KEY, "") ?: ""
    }

    fun loadUser() {
        Resources.User.info = UserInfo(
            prefs.getString(Resources.USER_ID_KEY, "") ?: "",
            prefs.getString(Resources.USERNAME_KEY, "") ?: ""
        )
    }

    fun updateToken(refreshToken: String) {
        prefs.edit()
            .putString(Resources.REFRESH_KEY, refreshToken)
            .apply()
    }

    fun clearToken() {
        prefs.edit()
            .putString(Resources.REFRESH_KEY, "")
            .apply()
    }

    fun saveUser() {
        prefs.edit()
            .putString(Resources.USERNAME_KEY, Resources.User.info.username)
            .putString(Resources.USER_ID_KEY, Resources.User.info.id)
            .apply()
    }

    fun clearUser() {
        prefs.edit()
            .putString(Resources.USERNAME_KEY, "")
            .putString(Resources.USER_ID_KEY, "")
            .apply()
    }
}