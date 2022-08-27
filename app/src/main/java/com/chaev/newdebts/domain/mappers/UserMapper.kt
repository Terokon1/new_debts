package com.chaev.newdebts.domain.mappers

import com.chaev.newdebts.data.models.base.UserResponse
import com.chaev.newdebts.domain.exceptions.MappingException
import com.chaev.newdebts.domain.models.base.UserInfo

object UserMapper {
    fun fromRaw(r: UserResponse) = UserInfo(
        r.id ?: throw MappingException("id"),
        r.username ?: throw MappingException("username")
    )
}