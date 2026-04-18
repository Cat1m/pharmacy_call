package com.hungduy.pharmacycall.ui.scan

sealed class ScanUiState {
    object Idle : ScanUiState()
    object Scanning : ScanUiState()
    data class CodeReady(val code: String) : ScanUiState()
    data class Error(val message: String) : ScanUiState()
}
