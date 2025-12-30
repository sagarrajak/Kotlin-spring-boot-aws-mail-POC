package com.debaterr.app.aws_demo.emailservice


import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

class Otp(
    val otp: String
)
@Service
class EmailService(
    private val webClientBuilder: WebClient.Builder
) {

    private val log = LoggerFactory.getLogger(javaClass);

    @Value($$"${mail.apikey}")
    private lateinit var  token: String;

    private val webClient = webClientBuilder.baseUrl("https://api.zeptomail.com/v1.1").build()

    private val senderDomain = "noreply@debaterr.com"

    fun sendSimpleMail(): ZeptoResponse? {
        val requestBody = ZeptoEmailTemplateRequest<Otp>(
            from = EmailAddress(address = "noreply@debaterr.com", name = "Debater app"),
            to = listOf(
                ToRecipient(email_address = EmailAddress(address = "sagarrajak858@gmail.com", name = "Sagar"))
            ),
            subject = "Your Verification Code",
            templateId = "2d6f.31a7ad804155724b.k1.780c1ba1-e54c-11f0-b14a-fae9afc80e45.19b6e09f229",
            Otp("1234")
        )
        return webClient.post()
            .uri("/email/template")
            .header("Authorization", "Zoho-enczapikey $token")
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .bodyValue(requestBody)
            .retrieve()
            .onStatus({ it.isError }) { response ->
                // This handles API errors (4xx, 5xx) before blocking
                response.bodyToMono(String::class.java).flatMap { errorBody ->
                    Mono.error(RuntimeException("ZeptoMail API Error: $errorBody"))
                }
            }
            .bodyToMono(ZeptoResponse::class.java)
            .block()

     }


}