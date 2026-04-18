package com.hungduy.pharmacycall.signalr

import com.hungduy.pharmacycall.util.Constants
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignalRService @Inject constructor() {

    private var connection: HubConnection? = null

    private val _connectionState = MutableStateFlow<ConnState>(ConnState.Disconnected)
    val connectionState: StateFlow<ConnState> = _connectionState

    private val _tokenReceived = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val tokenReceived: SharedFlow<String> = _tokenReceived

    sealed class ConnState {
        object Disconnected : ConnState()
        object Connecting : ConnState()
        data class Connected(val connectionId: String) : ConnState()
        data class Failed(val error: Throwable) : ConnState()
    }

    suspend fun connect() = withContext(Dispatchers.IO) {
        try {
            _connectionState.value = ConnState.Connecting
            val hub = HubConnectionBuilder.create(Constants.SIGNALR_URL).build()
            hub.on(Constants.SIGNALR_EVENT_SET_TOKEN, { token: String ->
                _tokenReceived.tryEmit(token)
            }, String::class.java)
            hub.onClosed { _connectionState.value = ConnState.Disconnected }
            hub.start().blockingAwait(15, TimeUnit.SECONDS)
            val id = hub.connectionId
                ?: throw IllegalStateException("connectionId is null after start()")
            connection = hub
            _connectionState.value = ConnState.Connected(id)
        } catch (t: Throwable) {
            _connectionState.value = ConnState.Failed(t)
        }
    }

    suspend fun disconnect() = withContext(Dispatchers.IO) {
        try {
            connection?.stop()?.blockingAwait(5, TimeUnit.SECONDS)
        } catch (_: Throwable) {
        }
        connection = null
        _connectionState.value = ConnState.Disconnected
    }
}
