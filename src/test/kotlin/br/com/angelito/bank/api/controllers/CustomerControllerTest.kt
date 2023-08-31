package br.com.angelito.bank.api.controllers

import br.com.angelito.bank.BaseTest
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc
import io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.io.File


class CustomerControllerTest : BaseTest() {
//    lateinit var wireMock: WireMockServer
//    lateinit var testServerUri: String

//    @BeforeEach
//    fun setUp() {
//        wireMock = WireMockServer()
//        wireMock.start()
//        testServerUri = wireMock.baseUrl()
//    }
//
//    @AfterEach
//    fun tearDown() {
//        wireMock.stop()
//    }

    @Test
    fun `must create a customer`() {
        val createCustomerBody = File("$mockPath/create_customer.json")
            .readText(Charsets.UTF_8)

        Given {
            spec(requestSpecification).log().all()
            body(createCustomerBody)
        } When {
            post("/customers")
        } Then {
            assertThat().statusCode(HttpStatus.CREATED.value())
            assertThat().body("message", equalTo("ok"))
        }
    }
}