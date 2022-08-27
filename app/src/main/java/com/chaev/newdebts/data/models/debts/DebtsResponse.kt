package com.chaev.newdebts.data.models.debts

import com.chaev.newdebts.data.models.base.UserResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DebtsResponse(
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "money")
    val money: String?,
    @field:Json(name = "creditor")
    val creditor: UserResponse?,
    @field:Json(name = "debtor")
    val debtor: UserResponse?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "created")
    val created: String?,
)
