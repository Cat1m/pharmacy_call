package com.hungduy.pharmacycall.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PrescriptionRequest(
    @Json(name = "Codetoathuoc") val codeToaThuoc: String
)
