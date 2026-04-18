package com.hungduy.pharmacycall.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hungduy.pharmacycall.signalr.SignalRService
import com.hungduy.pharmacycall.util.DeviceInfo
import com.hungduy.pharmacycall.util.QrUrlBuilder
import com.hungduy.pharmacycall.util.TokenStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signalR: SignalRService,
    private val tokenStorage: TokenStorage,
    private val deviceInfo: DeviceInfo
) : ViewModel() {

    private val _state = MutableStateFlow<LoginUiState>(LoginUiState.Connecting)
    val state: StateFlow<LoginUiState> = _state

    init {
        observeConnection()
        observeToken()
        start()
    }

    fun start() = viewModelScope.launch { signalR.connect() }

    fun retry() = viewModelScope.launch {
        signalR.disconnect()
        signalR.connect()
    }

    private fun observeConnection() = viewModelScope.launch {
        signalR.connectionState.collect { st ->
            _state.value = when (st) {
                is SignalRService.ConnState.Connecting -> LoginUiState.Connecting
                is SignalRService.ConnState.Connected -> {
                    val url = QrUrlBuilder.build(
                        deviceName = deviceInfo.getDeviceName(),
                        connectionId = st.connectionId,
                        epochMs = System.currentTimeMillis()
                    )
                    LoginUiState.Ready(url)
                }
                is SignalRService.ConnState.Failed ->
                    LoginUiState.Failed(st.error.message ?: "Unknown error")
                SignalRService.ConnState.Disconnected -> _state.value // giữ nguyên
            }
        }
    }

    private fun observeToken() = viewModelScope.launch {
        signalR.tokenReceived.collect { token ->
            tokenStorage.saveToken(token)
            signalR.disconnect()
            _state.value = LoginUiState.Success
        }
    }
}
