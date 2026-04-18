package com.hungduy.pharmacycall.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ToaThuoc(
    @Json(name = "ima") val ima: String?,
    @Json(name = "ma") val ma: String?,
    @Json(name = "code") val code: String?,
    @Json(name = "tenkho") val tenkho: String?,
    @Json(name = "codebenhnhan") val codebenhnhan: String?,
    @Json(name = "hotenbenhnhan") val hotenbenhnhan: String?,
    @Json(name = "ngaysinh") val ngaysinh: String?,
    @Json(name = "gioitinh") val gioitinh: String?,
    @Json(name = "sodt") val sodt: String?,
    @Json(name = "diachi") val diachi: String?,
    @Json(name = "tongtientoathuoc") val tongtientoathuoc: String?,
    @Json(name = "trangthai") val trangthai: String?,
    @Json(name = "ngayban") val ngayban: String?,
    @Json(name = "sohoadon") val sohoadon: String?,
    @Json(name = "ngaykham") val ngaykham: String?,
    @Json(name = "songay") val songay: String?,
    @Json(name = "ngayhen") val ngayhen: String?,
    @Json(name = "ghichu") val ghichu: String?,
    @Json(name = "tenbacsi") val tenbacsi: String?
)
