package com.hungduy.pharmacycall.ui.login

sealed class LoginUiState {
    object Connecting : LoginUiState()
    data class Ready(val qrUrl: String) : LoginUiState()
    data class Failed(val message: String) : LoginUiState()
    object Success : LoginUiState()
}
