package com.hungduy.pharmacycall.ui.scan

import androidx.lifecycle.ViewModel
import com.hungduy.pharmacycall.util.TokenStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val tokenStorage: TokenStorage
) : ViewModel() {

    private val _state = MutableStateFlow<ScanUiState>(ScanUiState.Idle)
    val state: StateFlow<ScanUiState> = _state

    fun setScanning() {
        _state.value = ScanUiState.Scanning
    }

    fun onCodeDetected(code: String) {
        _state.value = ScanUiState.CodeReady(code)
    }

    fun reset() {
        _state.value = ScanUiState.Idle
    }

    fun logout() {
        tokenStorage.clearToken()
    }
}
