package com.hungduy.pharmacycall.data.remote.api

import com.hungduy.pharmacycall.data.remote.model.ApiStatusResponse
import com.hungduy.pharmacycall.data.remote.model.BaseResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PharmacyApi {

    // Lấy danh sách quầy khu vực nhà thuốc (không cần gửi tham số)
    @POST("banLe/getQuayKhuVucNhaThuoc")
    suspend fun getQuayList(): BaseResponse

    // Lấy chi tiết toa thuốc theo mã (truyền tham số dạng Form)
    @FormUrlEncoded
    @POST("toathuoc/getChiTietToaThuocForApp")
    suspend fun getChiTietToaThuoc(
        @Field("Codetoathuoc") code: String
    ): BaseResponse

    // Gọi số thứ tự bệnh nhân theo quầy (truyền tham số dạng Form)
    @FormUrlEncoded
    @POST("goitennhanh/goiSTTBenhNhanTheoQuayNhaThuoc")
    suspend fun goiSttBenhNhan(
        @Field("CodeBenhNhan") codeBenhNhan: String,
        @Field("STT") stt: String,
        @Field("MaQuay") maQuay: String,
        @Field("MaKho") maKho: String
    ): ApiStatusResponse

    // Gửi tin nhắn lấy thuốc cho bệnh nhân (truyền tham số dạng Form)
    @FormUrlEncoded
    @POST("banLe/guiTinNhanLayThuoc")
    suspend fun guiTinNhanLayThuoc(
        @Field("Codetoathuoc") codeToaThuoc: String
    ): ApiStatusResponse
}