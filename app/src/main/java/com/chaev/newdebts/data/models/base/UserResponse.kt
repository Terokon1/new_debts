package com.chaev.newdebts.data.models.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "username")
    val username: String?
)