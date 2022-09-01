package com.chaev.newdebts.domain.repositories

import android.util.Log
import com.chaev.newdebts.data.SharedPrefManager
import com.chaev.newdebts.data.api.ApiService
import com.chaev.newdebts.data.models.login.LoginRequest
import com.chaev.newdebts.data.models.registration.RegistrationRequest
import com.chaev.newdebts.data.models.tokens.RefreshRequest
import com.chaev.newdebts.data.models.tokens.TokenRequest
import com.chaev.newdebts.domain.mappers.CreditsMapper
import com.chaev.newdebts.domain.mappers.DebtsMapper
import com.chaev.newdebts.domain.mappers.TokensMapper
import com.chaev.newdebts.domain.mappers.UserMapper
import com.chaev.newdebts.domain.models.Sums
import com.chaev.newdebts.domain.models.Tokens
import com.chaev.newdebts.utils.Resources
import com.chaev.newdebts.utils.Either

class DebtsApiRepository(private val api: ApiService, private val prefManager: SharedPrefManager) {
    private var accessToken: String = ""
    var refreshToken: String = ""

    init {
        refreshToken = prefManager.loadToken()
    }

    fun setupTokens(tokens: Tokens) {
        accessToken = "Bearer ${tokens.access}"
        refreshToken = tokens.refresh
    }

    suspend fun login(email: String, password: String) = Either.of {
        TokensMapper.fromRaw(api.authorize(LoginRequest(email, password)))
    }

    suspend fun register(email: String, password: String) = Either.of {
        api.register(RegistrationRequest(email, password))
    }

    suspend fun getMyInfo() = Either.of {
        UserMapper.fromRaw(api.getMyInfo(accessToken))
    }

    suspend fun getDebts() = Either.of {
        Log.d("zxc", "repo")
        DebtsMapper.fromRaw(api.getDebts(accessToken)).filter {
            it.debtor == Resources.User.info
        }
    }

    suspend fun getCredits() = Either.of {
        CreditsMapper.fromRaw(api.getDebts(accessToken)).filter {
            it.creditor == Resources.User.info
        }
    }

    suspend fun getSums() = Either.of {
        val debtsSum = DebtsMapper.fromRaw(api.getDebts(accessToken)).filter {
            it.debtor == Resources.User.info
        }.sumOf { it.money.toDouble() }
        val creditsSum = CreditsMapper.fromRaw(api.getDebts(accessToken)).filter {
            it.creditor == Resources.User.info
        }.sumOf { it.money.toDouble() }
        Sums(debtsSum, creditsSum)
    }

    suspend fun verifyToken() = Either.of {
        api.verifyToken(TokenRequest(refreshToken))
    }

    suspend fun getNewTokens() = Either.of {
        val response = TokensMapper.fromRaw(api.getNewTokens(RefreshRequest(refreshToken)))
        prefManager.updateToken(response.refresh)
        setupTokens(response)
    }
}