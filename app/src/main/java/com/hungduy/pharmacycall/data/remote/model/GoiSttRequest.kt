package com.hungduy.pharmacycall.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GoiSttRequest(
    @Json(name = "CodeBenhNhan") val codeBenhNhan: String,
    @Json(name = "STT") val stt: String,
    @Json(name = "MaQuay") val maQuay: String,
    @Json(name = "MaKho") val maKho: String
)
