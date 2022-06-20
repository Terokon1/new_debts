package com.chaev.newdebts.data.api

import com.chaev.newdebts.data.models.login.LoginRequest
import com.chaev.newdebts.data.models.registration.RegistrationRequest
import com.chaev.newdebts.data.models.tokens.TokensResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/accounts/token/")
    suspend fun authorize(@Body creds: LoginRequest): TokensResponse
    @POST("api/accounts/register/")
    suspend fun register(@Body creds: RegistrationRequest)
}