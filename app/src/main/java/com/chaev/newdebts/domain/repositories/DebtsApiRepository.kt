package com.chaev.newdebts.domain.repositories

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.chaev.newdebts.data.api.ApiService
import com.chaev.newdebts.data.models.login.LoginRequest
import com.chaev.newdebts.data.models.registration.RegistrationRequest
import com.chaev.newdebts.domain.mappers.CreditsMapper
import com.chaev.newdebts.domain.mappers.DebtsMapper
import com.chaev.newdebts.domain.mappers.TokensMapper
import com.chaev.newdebts.domain.mappers.UserMapper
import com.chaev.newdebts.domain.models.Tokens
import com.chaev.newdebts.utils.AppConsts
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

    suspend fun register(email: String, password: String) = Either.of {
        api.register(RegistrationRequest(email, password))
    }

    suspend fun getMyInfo() = Either.of {
        UserMapper.fromRaw(api.getMyInfo(accessToken))
    }

    suspend fun getDebts() = Either.of {
        DebtsMapper.fromRaw(api.getDebts(accessToken)).filter {
            it.debtor == AppConsts.User.info
        }
    }


    suspend fun getCredits() = Either.of {
        CreditsMapper.fromRaw(api.getDebts(accessToken)).filter {
            it.creditor == AppConsts.User.info
        }
    }
}