package br.com.angelito.bank.api.controllers

import br.com.angelito.bank.application.requests.CustomerRequest
import br.com.angelito.bank.infra.models.AccountModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(
    "/customers",
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
@ApiResponse(responseCode = "403", description = "Forbidden")
class CustomerController {
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        operationId = "createCustomer",
        summary = "Create a customer",
        description = "Create a customer",
        tags = ["Customers"],
        responses = [
            ApiResponse(responseCode = "201", description = "successful created"),
            ApiResponse(responseCode = "400", description = "validation error")
        ],
    )
    fun createCustomer(@Valid @RequestBody customerRequest: CustomerRequest): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"ok\"}")
    }
}