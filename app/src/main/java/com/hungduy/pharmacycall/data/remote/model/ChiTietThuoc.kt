package com.hungduy.pharmacycall.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChiTietThuoc(
    @Json(name = "stt") val stt: String?,
    @Json(name = "ma") val ma: String?,
    @Json(name = "matoathuoc") val matoathuoc: String?,
    @Json(name = "mavattu") val mavattu: String?,
    @Json(name = "tenvattu") val tenvattu: String?,
    @Json(name = "mahh") val mahh: String?,
    @Json(name = "tenhh") val tenhh: String?,
    @Json(name = "madvt") val madvt: String?,
    @Json(name = "tendvt") val tendvt: String?,
    @Json(name = "hamluong") val hamluong: String?,
    @Json(name = "songayuong") val songayuong: String?,
    @Json(name = "sang") val sang: String?,
    @Json(name = "trua") val trua: String?,
    @Json(name = "chieu") val chieu: String?,
    @Json(name = "toi") val toi: String?,
    @Json(name = "soluong") val soluong: String?,
    @Json(name = "ghichu") val ghichu: String?,
    @Json(name = "dongia") val dongia: String?,
    @Json(name = "thanhtien") val thanhtien: String?
)
