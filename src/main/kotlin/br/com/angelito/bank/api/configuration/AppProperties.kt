package br.com.angelito.bank.api.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppProperties {
    @Value("\${app.gateways.exchange-gateway}")
    lateinit var exchangeGateway:String
}