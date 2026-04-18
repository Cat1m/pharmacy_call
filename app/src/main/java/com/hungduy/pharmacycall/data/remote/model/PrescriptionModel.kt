package com.hungduy.pharmacycall.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PrescriptionModel(
    @Json(name = "makho") val makho: String?,
    @Json(name = "stt") val stt: String?,
    @Json(name = "toathuoc") val toathuoc: ToaThuoc?,
    @Json(name = "chitiet") val chitiet: List<ChiTietThuoc> = emptyList()
)
