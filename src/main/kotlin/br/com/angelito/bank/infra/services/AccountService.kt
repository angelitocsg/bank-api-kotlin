package br.com.angelito.bank.infra.services

import br.com.angelito.bank.infra.models.AccountModel
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

interface AccountService {
    fun create(@RequestBody account: AccountModel): AccountModel

    fun getAll(): List<AccountModel>

    fun getById(@PathVariable id: Long): Optional<AccountModel>

    fun update(@PathVariable id: Long, @RequestBody account: AccountModel): Optional<AccountModel>

    fun delete(@PathVariable id: Long)
}