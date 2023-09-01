package br.com.angelito.bank.api.controllers

import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.ListObjectsRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AwsController {
    @GetMapping
    suspend fun getObjects() {
        val request = ListObjectsRequest {
            bucket = "pocket-test-20230901"
        }

        S3Client { region = "sa-east-1" }.use { s3 ->
            val response = s3.listObjects(request)
            response.contents?.forEach { myObject ->
                println("The name of the key is ${myObject.key}")
                println("The object is ${calKb(myObject.size)} KBs")
                println("The owner is ${myObject.owner}")
            }
        }
    }

    private fun calKb(intValue: Long): Long {
        return intValue / 1024
    }

}