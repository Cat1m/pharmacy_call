package com.hungduy.pharmacycall.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hungduy.pharmacycall.R
import com.hungduy.pharmacycall.ui.components.ErrorMessage
import com.hungduy.pharmacycall.ui.components.LoadingIndicator
import com.hungduy.pharmacycall.ui.components.QuaySelectionDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrescriptionDetailScreen(
    onBack: () -> Unit,
    vm: DetailViewModel = hiltViewModel()
) {
    val state by vm.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    var showQuayDialog by remember { mutableStateOf(false) }
    var showCallConfirm by remember { mutableStateOf(false) }
    var showSendConfirm by remember { mutableStateOf(false) }

    LaunchedEffect(state.actionResult) {
        state.actionResult?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            vm.clearActionResult()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.detail_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.btn_back)
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        when {
            state.isLoading -> LoadingIndicator()
            state.errorMessage != null -> {
                ErrorMessage(
                    text = state.errorMessage!!,
                    onRetry = vm::reload
                )
            }
            state.prescription != null -> {
                val prescription = state.prescription!!
                val toa = prescription.toathuoc

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    ) {
                        // Nút chọn quầy
                        OutlinedButton(
                            onClick = { showQuayDialog = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = state.selectedQuay?.tenquay
                                    ?: stringResource(R.string.select_quay)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Card thông tin bệnh nhân
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = toa?.hotenbenhnhan ?: "-",
                                    style = MaterialTheme.typography.headlineLarge
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                InfoRow("STT", prescription.stt)
                                InfoRow("Trạng thái", toa?.trangthai)
                                InfoRow("Nhà thuốc", toa?.tenkho)
                                Spacer(modifier = Modifier.height(8.dp))
                                InfoRow("Ngày sinh", toa?.ngaysinh)
                                InfoRow("Giới tính", toa?.gioitinh)
                                InfoRow("SĐT", toa?.sodt)
                                Spacer(modifier = Modifier.height(8.dp))
                                InfoRow("Tổng tiền", toa?.tongtientoathuoc)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Danh sách thuốc
                        Text(
                            text = "Danh sách thuốc",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        prescription.chitiet.forEach { item ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = item.tenvattu ?: "-",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    item.hamluong?.let {
                                        Text(text = "Hàm lượng: $it", style = MaterialTheme.typography.bodyLarge)
                                    }
                                    Text(
                                        text = "SL: ${item.soluong ?: "-"} ${item.tendvt ?: ""}",
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                    Text(
                                        text = "S:${item.sang ?: "0"} T:${item.trua ?: "0"} C:${item.chieu ?: "0"} T:${item.toi ?: "0"}",
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(8.dp))

                        // Thông tin cuối
                        InfoRow("Ngày tái khám", toa?.ngayhen)
                        InfoRow("Mã toa", toa?.code)
                        InfoRow("Lời dặn", toa?.ghichu)
                        InfoRow("Bác sĩ", toa?.tenbacsi)
                    }

                    // Bottom action buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = { showSendConfirm = true },
                            enabled = !state.actionInProgress,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(stringResource(R.string.btn_send_message))
                        }
                        Button(
                            onClick = { showCallConfirm = true },
                            enabled = !state.actionInProgress,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(stringResource(R.string.btn_call_patient))
                        }
                    }
                }
            }
        }
    }

    // Quay selection dialog
    if (showQuayDialog) {
        QuaySelectionDialog(
            list = state.quayList,
            onSelect = { q ->
                vm.selectQuay(q)
                showQuayDialog = false
            },
            onDismiss = { showQuayDialog = false }
        )
    }

    // Confirm call patient dialog
    if (showCallConfirm) {
        AlertDialog(
            onDismissRequest = { showCallConfirm = false },
            title = { Text(stringResource(R.string.btn_call_patient)) },
            text = { Text(stringResource(R.string.confirm_call_patient)) },
            confirmButton = {
                TextButton(onClick = {
                    showCallConfirm = false
                    vm.callPatient()
                }) {
                    Text(stringResource(R.string.btn_ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { showCallConfirm = false }) {
                    Text(stringResource(R.string.btn_cancel))
                }
            }
        )
    }

    // Confirm send message dialog
    if (showSendConfirm) {
        AlertDialog(
            onDismissRequest = { showSendConfirm = false },
            title = { Text(stringResource(R.string.btn_send_message)) },
            text = { Text(stringResource(R.string.confirm_send_message)) },
            confirmButton = {
                TextButton(onClick = {
                    showSendConfirm = false
                    vm.sendMessage()
                }) {
                    Text(stringResource(R.string.btn_ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { showSendConfirm = false }) {
                    Text(stringResource(R.string.btn_cancel))
                }
            }
        )
    }
}

@Composable
private fun InfoRow(label: String, value: String?) {
    if (!value.isNullOrBlank()) {
        Row(modifier = Modifier.padding(vertical = 2.dp)) {
            Text(
                text = "$label: ",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
