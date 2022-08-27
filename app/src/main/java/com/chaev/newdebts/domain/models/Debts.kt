package com.chaev.newdebts.domain.models

import com.chaev.newdebts.data.models.base.UserResponse
import com.chaev.newdebts.domain.models.base.UserInfo
import com.squareup.moshi.Json
import java.time.OffsetDateTime

data class Debts(
    val id: String,
    val money: String,
    val creditor: UserInfo,
    val debtor: UserInfo,
    val description: String,
    val created: OffsetDateTime,
    val userCreditor: Boolean
)
