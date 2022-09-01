package com.chaev.newdebts.utils

import com.chaev.newdebts.domain.models.base.UserInfo

object Resources {
    const val REFRESH_KEY = "refresh token"
    const val USERNAME_KEY = "username"
    const val USER_ID_KEY = "user id"

    object User {
        var info = UserInfo("", "")
    }


}