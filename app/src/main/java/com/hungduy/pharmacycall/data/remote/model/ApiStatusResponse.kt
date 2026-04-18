package com.hungduy.pharmacycall.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiStatusResponse(
    @Json(name = "Status") val status: String,
    @Json(name = "Messenge") val messenge: String  // giữ nguyên typo của server
)
