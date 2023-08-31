package br.com.angelito.bank.initializers

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext

class PropertyOverrideContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext?> {

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        //TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
        //    applicationContext!!, "app.gateways.exchange-gateway=$testServerUri"
        //)
        // TestPropertySourceUtils.addPropertiesFilesToEnvironment(
        //   applicationContext, "application-test.properties"
        // )
    }
}