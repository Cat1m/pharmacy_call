// Thêm file BaseResponse.kt
package com.hungduy.pharmacycall.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse(
    @Json(name = "Status") val status: String,
    @Json(name = "Messenge") val messenge: String,
    @Json(name = "Data") val data: String? // BẮT BUỘC LÀ STRING VÌ SERVER TRẢ VỀ CHUỖI
)