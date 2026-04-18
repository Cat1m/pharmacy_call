package com.hungduy.pharmacycall.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.hungduy.pharmacycall.R
import com.hungduy.pharmacycall.data.remote.model.QuayKhuVucNhaThuoc

@Composable
fun QuaySelectionDialog(
    list: List<QuayKhuVucNhaThuoc>,
    onSelect: (QuayKhuVucNhaThuoc) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.select_quay)) },
        text = {
            LazyColumn {
                items(list) { q ->
                    ListItem(
                        headlineContent = { Text(q.tenquay) },
                        supportingContent = { Text(q.ma) },
                        modifier = Modifier.clickable { onSelect(q) }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.btn_cancel))
            }
        }
    )
}
