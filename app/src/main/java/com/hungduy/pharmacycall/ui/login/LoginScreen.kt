package com.hungduy.pharmacycall.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import androidx.compose.foundation.Image
import com.hungduy.pharmacycall.R
import com.hungduy.pharmacycall.ui.components.ErrorMessage
import com.hungduy.pharmacycall.ui.components.LoadingIndicator

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    vm: LoginViewModel = hiltViewModel()
) {
    val state by vm.state.collectAsStateWithLifecycle()

    LaunchedEffect(state) {
        if (state is LoginUiState.Success) onLoginSuccess()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_logo_placeholder),
                contentDescription = null,
                modifier = Modifier.size(96.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            when (val s = state) {
                LoginUiState.Connecting -> {
                    LoadingIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.login_status_connecting),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                is LoginUiState.Ready -> {
                    AsyncImage(
                        model = s.qrUrl,
                        contentDescription = "QR",
                        modifier = Modifier.size(300.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.login_status_ready),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                is LoginUiState.Failed -> {
                    ErrorMessage(
                        text = "${stringResource(R.string.login_status_failed)}: ${s.message}",
                        onRetry = vm::retry
                    )
                }
                LoginUiState.Success -> {
                    LoadingIndicator()
                }
            }
        }
    }
}
