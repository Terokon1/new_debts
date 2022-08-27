package com.chaev.newdebts.domain.mappers

import com.chaev.newdebts.data.models.debts.DebtsResponse
import com.chaev.newdebts.domain.exceptions.MappingException
import com.chaev.newdebts.domain.models.Debts
import com.chaev.newdebts.domain.models.base.UserInfo
import java.time.OffsetDateTime

object DebtsMapper {
        fun fromRaw(r: List<DebtsResponse>) = r.map {
            Debts(
                it.id ?: throw MappingException("id"),
                it.money ?: throw MappingException("id"),
                UserInfo(
                    it.creditor?.id ?: throw MappingException("id"),
                    it.creditor.username ?: throw MappingException("id")
                ),
                UserInfo(
                    it.debtor?.id ?: throw MappingException("id"),
                    it.debtor.username ?: throw MappingException("id")
                ),
                it.description ?: throw MappingException("id"),
                OffsetDateTime.parse(it.created)  ?: throw MappingException("id"),
                false
            )
        }
}