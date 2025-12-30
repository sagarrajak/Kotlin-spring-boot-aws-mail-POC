package com.debaterr.app.aws_demo.emailservice

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class OtpRequest(
    val email: String,
    val otp: String
)

data class EmailResponse(
    val success: Boolean,
    val message: String,
    val data: ZeptoResponse?
)


@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val emailService: EmailService) {
    @PostMapping("/send-otp")
    fun sendOtp(@RequestBody request: OtpRequest): ResponseEntity<EmailResponse> {
        try {
            val response = emailService.sendSimpleMail()
            return ResponseEntity.ok(EmailResponse(success = true, message = "Email sent successfully", data = response))
        } catch (e: Exception) {
            return ResponseEntity.status(500).body(
                EmailResponse(success = false, message = "Failed to send email: ${e.message}", data = null)
            )
        }
    }
}