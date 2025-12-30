package com.debaterr.app.aws_demo.emailservice

import com.fasterxml.jackson.annotation.JsonProperty

data class ZeptoWebhookPayload(
    @JsonProperty("event_type") val eventType: String?,
    @JsonProperty("event_time") val eventTime: String?,
    @JsonProperty("mail_agent_id") val mailAgentId: String?,
    @JsonProperty("data") val data: WebhookData?
)

data class WebhookData(
    @JsonProperty("message_id") val messageId: String?,
    @JsonProperty("subject") val subject: String?,
    @JsonProperty("to_address") val toAddress: String?,
    @JsonProperty("bounce_type") val bounceType: String?, // Only for bounce events
    @JsonProperty("reason") val reason: String?
)