package com.example.test.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.test.ui.screens.CheckoutScreen
import com.example.test.ui.screens.KeamananScreen
import com.example.test.ui.screens.KonfirmasiScreen
import com.example.test.ui.screens.LoginScreen
import com.example.test.ui.screens.LokasiRestoranScreen
import com.example.test.ui.screens.MainScreen
import com.example.test.ui.screens.MetodePembayaranScreen
import com.example.test.ui.screens.PengaturanScreen
import com.example.test.ui.screens.SignInScreen
import com.example.test.ui.screens.SignUpScreen
import com.example.test.ui.screens.SplashScreen
import com.example.test.ui.screens.StatusPesananScreen
import com.example.test.ui.screens.TentangKamiScreen
import com.example.test.ui.viewmodel.OrderViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: OrderViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onSignInClicked = { navController.navigate(Screen.SignIn.route) },
                onSignUpClicked = { navController.navigate(Screen.SignUp.route) }
            )
        }

        composable(Screen.SignIn.route) {
            SignInScreen(
                viewModel = viewModel,
                onSignInSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route) {
                        popUpTo(Screen.SignIn.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(
                viewModel = viewModel,
                onSignUpSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToSignIn = {
                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(Screen.SignUp.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Main.route) {
            MainScreen(
                viewModel = viewModel,
                onNavigateToCheckout = { navController.navigate(Screen.Checkout.route) },
                onNavigateToSettings = { route -> navController.navigate(route) },
                onNavigateToStatusPesanan = { navController.navigate(Screen.StatusPesanan.route) }
            )
        }

        composable(Screen.Checkout.route) {
            CheckoutScreen(
                cartItems = uiState.cartItems,
                subtotal = viewModel.getCartTotal(),
                onQuantityChanged = { id, quantity ->
                    viewModel.updateCartQuantity(id, quantity)
                },
                onPlaceOrder = {
                    viewModel.placeOrder()
                    navController.navigate("konfirmasi") {
                        popUpTo(Screen.Checkout.route) { inclusive = true }
                    }
                },
                onBackClicked = { navController.popBackStack() }
            )
        }

        composable("konfirmasi") {
            KonfirmasiScreen(
                onBackToMenu = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo("konfirmasi") { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.StatusPesanan.route) {
            StatusPesananScreen(
                currentStatus = uiState.activeOrderStatus,
                onConfirmDelivery = {
                    viewModel.confirmOrderDelivery()
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.StatusPesanan.route) { inclusive = true }
                    }
                },
                onBackClicked = { navController.popBackStack() }
            )
        }

        composable(Screen.Pengaturan.route) {
            PengaturanScreen(onBackClicked = { navController.popBackStack() })
        }

        composable(Screen.Keamanan.route) {
            KeamananScreen(onBackClicked = { navController.popBackStack() })
        }

        composable(Screen.MetodePembayaran.route) {
            MetodePembayaranScreen(
                currentMethod = uiState.selectedPaymentMethod,
                onMethodSelected = { viewModel.selectPaymentMethod(it) },
                onBackClicked = { navController.popBackStack() }
            )
        }

        composable(Screen.LokasiRestoran.route) {
            LokasiRestoranScreen(onBackClicked = { navController.popBackStack() })
        }

        composable(Screen.TentangKami.route) {
            TentangKamiScreen(onBackClicked = { navController.popBackStack() })
        }
    }
}
