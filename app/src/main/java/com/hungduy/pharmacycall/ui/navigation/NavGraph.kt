package com.hungduy.pharmacycall.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hungduy.pharmacycall.ui.detail.PrescriptionDetailScreen
import com.hungduy.pharmacycall.ui.login.LoginScreen
import com.hungduy.pharmacycall.ui.scan.PrescriptionScanScreen

@Composable
fun PharmacyNavGraph(
    navController: NavHostController,
    startDestination: String = Routes.LOGIN
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.SCAN) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.SCAN) {
            PrescriptionScanScreen(
                onCodeReady = { code ->
                    navController.navigate(Routes.detail(code))
                },
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SCAN) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("code") { type = NavType.StringType })
        ) {
            PrescriptionDetailScreen(onBack = { navController.popBackStack() })
        }
    }
}
