package com.chaev.newdebts.data.models.tokens

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokensResponse(
    @field:Json(name = "access")
    val access: String?,
    @field:Json(name = "refresh")
    val refresh: String?
)