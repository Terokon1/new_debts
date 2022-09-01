package com.chaev.newdebts.data.api

import com.chaev.newdebts.data.models.base.UserResponse
import com.chaev.newdebts.data.models.debts.DebtsResponse
import com.chaev.newdebts.data.models.login.LoginRequest
import com.chaev.newdebts.data.models.registration.RegistrationRequest
import com.chaev.newdebts.data.models.tokens.RefreshRequest
import com.chaev.newdebts.data.models.tokens.TokenRequest
import com.chaev.newdebts.data.models.tokens.TokensResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/api/accounts/token/")
    suspend fun authorize(@Body creds: LoginRequest): TokensResponse

    @POST("api/accounts/register/")
    suspend fun register(@Body creds: RegistrationRequest)

    @GET("api/debts/")
    suspend fun getDebts(@Header("Authorization") accessToken: String): List<DebtsResponse>

    @GET("api/accounts/me/")
    suspend fun getMyInfo(@Header("Authorization") accessToken: String): UserResponse

    @POST("api/accounts/token/verify/")
    suspend fun verifyToken(@Body refreshToken: TokenRequest)

    @POST("api/accounts/token/refresh/")
    suspend fun getNewTokens(@Body refreshToken: RefreshRequest) : TokensResponse
}