package com.hungduy.pharmacycall.ui.navigation

object Routes {
    const val LOGIN = "login"
    const val SCAN = "scan"
    const val DETAIL = "detail/{code}"
    fun detail(code: String) = "detail/$code"
}
