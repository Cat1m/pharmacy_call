package com.hungduy.pharmacycall.ui.scan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.hungduy.pharmacycall.R

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PrescriptionScanScreen(
    onCodeReady: (String) -> Unit,
    onLogout: () -> Unit,
    vm: ScanViewModel = hiltViewModel()
) {
    val state by vm.state.collectAsStateWithLifecycle()
    var showManualDialog by remember { mutableStateOf(false) }
    val cameraPermission = rememberPermissionState(android.Manifest.permission.CAMERA)

    LaunchedEffect(state) {
        if (state is ScanUiState.CodeReady) {
            onCodeReady((state as ScanUiState.CodeReady).code)
            vm.reset()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.scan_title)) },
                actions = {
                    IconButton(onClick = {
                        vm.logout()
                        onLogout()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = stringResource(R.string.btn_logout)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        when (state) {
            ScanUiState.Scanning -> {
                if (cameraPermission.status.isGranted) {
                    QrScannerView(
                        onDetected = { vm.onCodeDetected(it) },
                        onClose = { vm.reset() }
                    )
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        val rationaleText = if (cameraPermission.status.shouldShowRationale) {
                            "Ứng dụng cần quyền camera để quét QR toa thuốc."
                        } else {
                            "Cần quyền camera để quét QR toa thuốc. Vui lòng cấp quyền."
                        }
                        Text(text = rationaleText)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { cameraPermission.launchPermissionRequest() }) {
                            Text("Cấp quyền Camera")
                        }
                    }
                }
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.scan_empty),
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                    Button(onClick = {
                        if (cameraPermission.status.isGranted) {
                            vm.setScanning()
                        } else {
                            cameraPermission.launchPermissionRequest()
                        }
                    }) {
                        Text(stringResource(R.string.btn_scan_qr))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedButton(onClick = { showManualDialog = true }) {
                        Text(stringResource(R.string.btn_manual_input))
                    }
                }
            }
        }
    }

    if (showManualDialog) {
        ManualInputDialog(
            onConfirm = { code ->
                showManualDialog = false
                vm.onCodeDetected(code)
            },
            onDismiss = { showManualDialog = false }
        )
    }
}
