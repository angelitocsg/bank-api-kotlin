package br.com.angelito.bank

import br.com.angelito.bank.initializers.WireMockInitializer
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.config.LogConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.filter.log.LogDetail
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = [WireMockInitializer::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BaseTest {

    companion object {
        lateinit var requestSpecification: RequestSpecification
    }

    @LocalServerPort
    val port = 0

    val pathProject: String = System.getProperty("user.dir")
    val mockPath: String get() = "$pathProject/src/main/resources/mocks"

    @BeforeAll
    open fun setUp() {
        val logConfig = LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)
        val config = RestAssuredConfig.config().logConfig(logConfig)
        val baseUrl = "http://localhost:$port"

        requestSpecification = RequestSpecBuilder()
            .setBaseUri(baseUrl)
            .setContentType(ContentType.JSON)
            .setRelaxedHTTPSValidation()
            .setConfig(config)
            .build()
    }

    @AfterAll
    open fun tearDown() {
        RestAssured.reset()
    }
}



