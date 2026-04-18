package com.hungduy.pharmacycall.util

import android.net.Uri

object QrUrlBuilder {
    fun build(deviceName: String, connectionId: String, epochMs: Long): String {
        val safeDevice = deviceName.replace("\"", "\\\"")
        val payloadJson = """{"device":"$safeDevice","id":"$connectionId","time":$epochMs}"""
        val encoded = Uri.encode(payloadJson)
        return "${Constants.QR_IMAGE_BASE}?data=$encoded" +
                "&light=${Constants.QR_LIGHT}" +
                "&dark=${Constants.QR_DARK}" +
                "&data_dark=${Constants.QR_DATA_DARK}"
    }
}
