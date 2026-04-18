package com.hungduy.pharmacycall.util

import android.os.Build
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceInfo @Inject constructor() {
    fun getDeviceName(): String =
        "${Build.MANUFACTURER} ${Build.MODEL}".trim()
}
