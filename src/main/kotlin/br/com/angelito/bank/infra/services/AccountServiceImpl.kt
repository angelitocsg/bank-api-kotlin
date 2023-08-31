package br.com.angelito.bank.infra.services

import br.com.angelito.bank.infra.models.AccountModel
import br.com.angelito.bank.infra.repositories.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.util.*

@Service
class AccountServiceImpl(private val repository: AccountRepository) : AccountService {
    override fun create(account: AccountModel): AccountModel {
        Assert.hasLength(account.name, "[nome] não pode estar em branco!")
        Assert.isTrue(account.name.length >= 5, "[nome] deve ter no mínimo 5 caracteres")
        return repository.save(account)
    }

    override fun getAll(): List<AccountModel> {
        return repository.findAll()
    }

    override fun getById(id: Long): Optional<AccountModel> {
        return repository.findById(id)
    }

    override fun update(id: Long, account: AccountModel): Optional<AccountModel> {
        val optional = getById(id)
        if (optional.isEmpty) return Optional.empty<AccountModel>()

        return optional.map {
            val accountToUpdate = it.copy(
                name = account.name,
                document = account.document,
                phone = account.phone
            )
            repository.save(accountToUpdate)
        }

    }

    override fun delete(id: Long) {
        repository.findById(id).map {
            repository.delete(it)
        }.orElseThrow { throw RuntimeException("Id not found") }
    }
}