package com.hungduy.pharmacycall.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuayKhuVucNhaThuoc(
    @Json(name = "ma") val ma: String,
    @Json(name = "tenquay") val tenquay: String
)
