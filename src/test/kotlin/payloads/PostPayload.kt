package payloads

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PostPayload(val title: String, val body: String, val userId: Int)