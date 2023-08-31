package br.com.angelito.bank.infra.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "accounts")
data class AccountModel(
    @Id
    @GeneratedValue
    var id: Long? = null,
    val name: String,
    val document: String,
    val phone: String
)