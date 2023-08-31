package br.com.angelito.bank.initializers

import com.github.tomakehurst.wiremock.WireMockServer
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent
import org.springframework.test.context.support.TestPropertySourceUtils

class WireMockInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    lateinit var wireMockServer: WireMockServer
    lateinit var testServerUri: String

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        wireMockServer = WireMockServer(0)
        wireMockServer.start()
        testServerUri = wireMockServer.baseUrl()

        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
            applicationContext!!, "app.gateways.exchange-gateway=$testServerUri"
        )

        applicationContext.addApplicationListener { applicationEvent ->
            if (applicationEvent is ContextClosedEvent) {
                wireMockServer.stop()
            }
        }
    }
}