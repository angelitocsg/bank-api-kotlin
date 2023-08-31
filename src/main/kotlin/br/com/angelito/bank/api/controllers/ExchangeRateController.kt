package br.com.angelito.bank.api.controllers

import br.com.angelito.bank.api.configuration.AppProperties
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import javax.validation.Valid

@RestController
@RequestMapping(
    "/exchange-rate",
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
@ApiResponse(responseCode = "403", description = "Forbidden")
class ExchangeRateController {
    @Autowired
    lateinit var appProperties: AppProperties

    @GetMapping("{currency}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        operationId = "getExchangeRate",
        summary = "Get exchange rate",
        description = "Get exchange rate",
        tags = ["Exchange Rates"],
        responses = [
            ApiResponse(responseCode = "201", description = "successful created"),
            ApiResponse(responseCode = "400", description = "validation error")
        ],
    )
    fun getExchangeRate(@Valid @PathVariable("currency") currency: String): ResponseEntity<ExchangeRateDetails> {
        val restTemplate = RestTemplate()
        val response = restTemplate.getForObject(
            "${appProperties.exchangeGateway}/last/{currency}",
            ExchangeRate::class.java,
            currency
        )

        return ResponseEntity.status(HttpStatus.OK).body(response?.USDBRL)
    }

}

data class ExchangeRate(
    @JsonProperty("USDBRL")
    val USDBRL: ExchangeRateDetails
)

data class ExchangeRateDetails(
    @JsonProperty("code")
    val code: String,
    @JsonProperty("codein")
    val codein: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("high")
    val high: String,
    @JsonProperty("low")
    val low: String,
    @JsonProperty("timestamp")
    val timestamp: String,
    @JsonProperty("create_date")
    val createDate: String,
)