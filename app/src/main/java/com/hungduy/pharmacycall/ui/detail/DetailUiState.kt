package com.hungduy.pharmacycall.ui.detail

import com.hungduy.pharmacycall.data.remote.model.PrescriptionModel
import com.hungduy.pharmacycall.data.remote.model.QuayKhuVucNhaThuoc

data class DetailUiState(
    val isLoading: Boolean = true,
    val prescription: PrescriptionModel? = null,
    val quayList: List<QuayKhuVucNhaThuoc> = emptyList(),
    val selectedQuay: QuayKhuVucNhaThuoc? = null,
    val errorMessage: String? = null,
    val actionInProgress: Boolean = false,
    val actionResult: String? = null  // toast/snackbar
)
