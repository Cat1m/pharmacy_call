package com.hungduy.pharmacycall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.hungduy.pharmacycall.ui.login.LoginUiState
import com.hungduy.pharmacycall.ui.login.LoginViewModel
import com.hungduy.pharmacycall.ui.navigation.PharmacyNavGraph
import com.hungduy.pharmacycall.ui.theme.PharmacyCallTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Khởi tạo LoginViewModel ở Activity scope
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // BẮT BUỘC: installSplashScreen() phải được gọi TRƯỚC super.onCreate()
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        // Giữ Splash Screen hiển thị nếu SignalR đang Connecting
        splashScreen.setKeepOnScreenCondition {
            loginViewModel.state.value is LoginUiState.Connecting
        }

        setContent {
            PharmacyCallTheme {
                val nav = rememberNavController()
                PharmacyNavGraph(navController = nav)
            }
        }
    }
}