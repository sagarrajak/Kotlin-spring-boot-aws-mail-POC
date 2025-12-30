package com.debaterr.app.aws_demo.emailservice

import com.fasterxml.jackson.annotation.JsonProperty

data class ZeptoEmailRequest(
    val from: EmailAddress,
    val to: List<ToRecipient>,
    val subject: String,
    val htmlbody: String
)

data class ZeptoEmailTemplateRequest(
    val from: EmailAddress,
    val to: List<ToRecipient>,
    val cc: List<ToRecipient>?,
    val bcc: List<ToRecipient>?,
    val subject: String,
    @JsonProperty("template_key")
    val templateId: String
) {
    constructor(from: EmailAddress, to: List<ToRecipient>, subject: String, templateId: String) : this(from , to, null, null, subject, templateId)
}

data class ToRecipient(
    val email_address: EmailAddress
)

data class EmailAddress(
    val address: String,
    val name: String? = null
)


// Response data
data class ZeptoResponse(
    val data: List<ZeptoData>,
    val message: String,
    @JsonProperty("request_id")
    val requestId: String,
    @JsonProperty("object")
    val objectType: String
)

data class ZeptoData(
    val code: String,
    @JsonProperty("additional_info")
    val additionalInfo: List<Any>, // Usually an empty list or map
    val message: String
)