package org.example.serversidekotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServerSideKotlinApplication

fun main(args: Array<String>) {
    runApplication<ServerSideKotlinApplication>(*args)
}
