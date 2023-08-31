package br.com.angelito.bank.api.controllers

import br.com.angelito.bank.infra.models.AccountModel
import br.com.angelito.bank.infra.services.AccountService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    "/accounts",
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
@ApiResponse(responseCode = "403", description = "Forbidden")
class AccountController(private val service: AccountService) {
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        operationId = "createAccount",
        summary = "Create an account",
        description = "Create an account",
        tags = ["Accounts"],
        responses = [
            ApiResponse(responseCode = "201", description = "successful created"),
            ApiResponse(responseCode = "400", description = "validation error")
        ],
    )
    fun create(@RequestBody account: AccountModel): AccountModel = service.create(account)

    @GetMapping
    @Operation(
        operationId = "getAllAccounts",
        summary = "Return all accounts",
        description = "Return all accounts",
        tags = ["Accounts"],
        responses = [
            ApiResponse(responseCode = "200", description = "successful"),
            ApiResponse(responseCode = "404", description = "no accounts found")
        ],
    )
    fun getAll(): List<AccountModel> = service.getAll()

    @GetMapping("/{id}")
    @Operation(
        operationId = "getByID",
        summary = "Return an account by id",
        description = "Return an account by id",
        tags = ["Accounts"],
        parameters = [Parameter(name = "id", description = "account id")],
        responses = [
            ApiResponse(responseCode = "200", description = "successful"),
            ApiResponse(responseCode = "404", description = "no account found")
        ]
    )
    fun getById(@PathVariable id: Long): ResponseEntity<AccountModel> = service.getById(id).map {
        ResponseEntity.ok(it)
    }.orElse(ResponseEntity.notFound().build())


    @PutMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(
        operationId = "updateAccount",
        summary = "Update an account",
        description = "Update an account",
        tags = ["Accounts"],
        parameters = [Parameter(name = "id", description = "account id")],
        responses = [
            ApiResponse(responseCode = "200", description = "successful"),
            ApiResponse(responseCode = "404", description = "no account found")
        ]
    )
    fun update(@PathVariable id: Long, @RequestBody account: AccountModel): ResponseEntity<AccountModel> =
        service.update(id, account).map {
            ResponseEntity.ok(it)
        }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    @Operation(
        operationId = "deleteAccount",
        summary = "Delete an account",
        description = "Delete an account",
        tags = ["Accounts"],
        parameters = [Parameter(name = "id", description = "account id")],
        responses = [
            ApiResponse(responseCode = "200", description = "successful"),
            ApiResponse(responseCode = "500", description = "no account found")
        ]
    )
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}