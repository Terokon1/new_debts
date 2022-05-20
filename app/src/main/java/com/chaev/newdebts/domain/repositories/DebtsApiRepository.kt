package com.chaev.newdebts.domain.repositories

import com.chaev.newdebts.data.api.ApiService
import com.chaev.newdebts.data.models.login.LoginRequest
import com.chaev.newdebts.domain.mappers.TokensMapper
import com.chaev.newdebts.domain.models.Tokens
import com.chaev.newdebts.utils.Either

class DebtsApiRepository(private val api: ApiService) {
    var accessToken: String = ""
    var refreshToken: String = ""

    fun setupTokens(tokens: Tokens) {
        accessToken = "Bearer ${tokens.access}"
        refreshToken = tokens.refresh
    }

    suspend fun login(email: String, password: String) = Either.of {
        TokensMapper.fromRaw(api.authorize(LoginRequest(email, password)))
    }
}