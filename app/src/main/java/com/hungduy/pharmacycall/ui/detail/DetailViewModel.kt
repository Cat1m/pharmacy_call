package com.hungduy.pharmacycall.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hungduy.pharmacycall.data.remote.model.GoiSttRequest
import com.hungduy.pharmacycall.data.remote.model.QuayKhuVucNhaThuoc
import com.hungduy.pharmacycall.domain.repository.PharmacyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repo: PharmacyRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val code: String = checkNotNull(savedStateHandle["code"])

    private val _state = MutableStateFlow(DetailUiState())
    val state: StateFlow<DetailUiState> = _state

    init {
        load()
    }

    fun reload() = load()

    private fun load() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true, errorMessage = null) }
        val pres = repo.getPrescription(code)
        val quay = repo.getQuayList()
        _state.update {
            it.copy(
                isLoading = false,
                prescription = pres.getOrNull(),
                quayList = quay.getOrNull() ?: emptyList(),
                errorMessage = (pres.exceptionOrNull() ?: quay.exceptionOrNull())?.message
            )
        }
    }

    fun selectQuay(q: QuayKhuVucNhaThuoc) {
        _state.update { it.copy(selectedQuay = q) }
    }

    fun clearActionResult() {
        _state.update { it.copy(actionResult = null) }
    }

    fun callPatient() = viewModelScope.launch {
        val s = _state.value
        val q = s.selectedQuay
        val p = s.prescription ?: return@launch
        if (q == null) {
            _state.update { it.copy(actionResult = "Vui lòng chọn quầy thuốc") }
            return@launch
        }
        _state.update { it.copy(actionInProgress = true) }
        val res = repo.callPatient(
            GoiSttRequest(
                codeBenhNhan = p.toathuoc?.codebenhnhan.orEmpty(),
                stt = p.stt.orEmpty(),
                maQuay = q.ma,
                maKho = p.makho.orEmpty()
            )
        )
        _state.update {
            it.copy(
                actionInProgress = false,
                actionResult = res.getOrNull()?.messenge ?: res.exceptionOrNull()?.message
            )
        }
    }

    fun sendMessage() = viewModelScope.launch {
        _state.update { it.copy(actionInProgress = true) }
        val res = repo.sendMessage(code)
        _state.update {
            it.copy(
                actionInProgress = false,
                actionResult = res.getOrNull()?.messenge ?: res.exceptionOrNull()?.message
            )
        }
    }
}
