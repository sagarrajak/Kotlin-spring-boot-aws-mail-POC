package com.debaterr.app.aws_demo.emailservice

import io.github.cdimascio.dotenv.dotenv
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class Config {
    @PostConstruct
    fun loadEnv() {
        val dotenv = dotenv {
            directory = "./src/main/resources" // Path to your resources
            filename = ".env"
            ignoreIfMissing = true
        }
        for(e in dotenv.entries()) {
            System.setProperty(e.key.toString(), e.value);
        }
    }

    @Bean
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
            // You can add global settings here, like timeouts or default headers
            .codecs { configurer ->
                configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024) // 2MB buffer
            }
    }
}