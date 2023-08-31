package br.com.angelito.bank.infra.repositories

import br.com.angelito.bank.infra.models.AccountModel
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<AccountModel, Long> {
}