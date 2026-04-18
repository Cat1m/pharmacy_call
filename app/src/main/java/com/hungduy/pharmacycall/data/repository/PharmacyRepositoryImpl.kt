package com.hungduy.pharmacycall.data.repository

import com.hungduy.pharmacycall.data.remote.api.PharmacyApi
import com.hungduy.pharmacycall.data.remote.model.ApiStatusResponse
import com.hungduy.pharmacycall.data.remote.model.GoiSttRequest
import com.hungduy.pharmacycall.data.remote.model.PrescriptionModel
import com.hungduy.pharmacycall.data.remote.model.QuayKhuVucNhaThuoc
import com.hungduy.pharmacycall.domain.repository.PharmacyRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PharmacyRepositoryImpl @Inject constructor(
    private val api: PharmacyApi,
    private val moshi: Moshi
) : PharmacyRepository {

    // Gọi API lấy danh sách quầy và bóc tách dữ liệu từ chuỗi JSON
    override suspend fun getQuayList(): Result<List<QuayKhuVucNhaThuoc>> =
        runCatching {
            val response = api.getQuayList()
            if (response.status == "OK" && !response.data.isNullOrEmpty()) {
                val type = Types.newParameterizedType(List::class.java, QuayKhuVucNhaThuoc::class.java)
                val adapter = moshi.adapter<List<QuayKhuVucNhaThuoc>>(type)
                adapter.fromJson(response.data) ?: emptyList()
            } else {
                throw Exception(response.messenge.ifEmpty { "Lỗi lấy danh sách quầy" })
            }
        }

    // Gọi API lấy chi tiết toa thuốc và bóc tách dữ liệu từ chuỗi JSON
    override suspend fun getPrescription(code: String): Result<PrescriptionModel> =
        runCatching {
            val response = api.getChiTietToaThuoc(code)
            if (response.status == "OK" && !response.data.isNullOrEmpty()) {
                val adapter = moshi.adapter(PrescriptionModel::class.java)
                adapter.fromJson(response.data) ?: throw Exception("Dữ liệu rỗng")
            } else {
                throw Exception(response.messenge.ifEmpty { "Không tìm thấy toa" })
            }
        }

    // Gửi request gọi số thứ tự bệnh nhân trực tiếp qua Form
    override suspend fun callPatient(req: GoiSttRequest): Result<ApiStatusResponse> =
        runCatching {
            api.goiSttBenhNhan(
                codeBenhNhan = req.codeBenhNhan,
                stt = req.stt,
                maQuay = req.maQuay,
                maKho = req.maKho
            )
        }

    // Gửi request tin nhắn lấy thuốc trực tiếp qua Form
    override suspend fun sendMessage(code: String): Result<ApiStatusResponse> =
        runCatching {
            api.guiTinNhanLayThuoc(code)
        }
}