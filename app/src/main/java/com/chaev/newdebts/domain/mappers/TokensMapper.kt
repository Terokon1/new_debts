package com.chaev.newdebts.domain.mappers

import com.chaev.newdebts.data.models.tokens.TokensResponse
import com.chaev.newdebts.domain.exceptions.MappingException
import com.chaev.newdebts.domain.models.Tokens

object TokensMapper {
    fun fromRaw(r: TokensResponse) = Tokens(
        r.access ?: throw MappingException("access"),
        r.refresh ?: throw MappingException("refresh")
    )
}