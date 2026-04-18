package com.hungduy.pharmacycall.domain.repository

import com.hungduy.pharmacycall.data.remote.model.ApiStatusResponse
import com.hungduy.pharmacycall.data.remote.model.GoiSttRequest
import com.hungduy.pharmacycall.data.remote.model.PrescriptionModel
import com.hungduy.pharmacycall.data.remote.model.QuayKhuVucNhaThuoc

interface PharmacyRepository {
    suspend fun getQuayList(): Result<List<QuayKhuVucNhaThuoc>>
    suspend fun getPrescription(code: String): Result<PrescriptionModel>
    suspend fun callPatient(req: GoiSttRequest): Result<ApiStatusResponse>
    suspend fun sendMessage(code: String): Result<ApiStatusResponse>
}
