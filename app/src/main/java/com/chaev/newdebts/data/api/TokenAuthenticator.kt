package com.chaev.newdebts.data.api

import com.chaev.newdebts.data.SharedPrefManager
import com.chaev.newdebts.domain.repositories.DebtsApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TokenAuthenticator: Authenticator, KoinComponent {
    private val debtsApiRepository: DebtsApiRepository by inject()

    override fun authenticate(route: Route?, response: Response): Request? {
   //CoroutineScope(Dispatchers.IO).launch {
   //    debtsApiRepository.getNewTokens()
   //}
    return null
    }
}