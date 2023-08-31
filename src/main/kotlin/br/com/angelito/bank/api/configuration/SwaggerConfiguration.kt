package br.com.angelito.bank.api.configuration

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "Bank Account API",
        description = "Customers, bank accounts and transactions"
    )
)
class SwaggerConfiguration