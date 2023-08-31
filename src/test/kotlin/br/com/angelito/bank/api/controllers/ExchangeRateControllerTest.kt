package br.com.angelito.bank.api.controllers

import br.com.angelito.bank.BaseTest
import com.github.tomakehurst.wiremock.WireMockServer
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class ExchangeRateControllerTest : BaseTest() {
    @Test
    fun `must return USD-BRL exchange rate`() {
        val exchangeResponse: Response = Given {
            spec(requestSpecification).log().all()
        } When {
            get("/exchange-rate/USD-BRL")

        } Then {
            assertThat().statusCode(HttpStatus.OK.value())
            assertThat().body("code", equalTo("USD"))
            assertThat().body("codein", equalTo("BRL"))
            assertThat().body("timestamp", equalTo("1693490645"))
        } Extract {
            response()
        }
        println(exchangeResponse)
    }
}