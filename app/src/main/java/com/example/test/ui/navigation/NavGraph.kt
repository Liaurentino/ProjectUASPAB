package com.example.test.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.test.ui.screens.CheckoutScreen
import com.example.test.ui.screens.KeamananScreen
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
import com.example.test.ui.screens.EditProfilScreen
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
                    navController.navigate(Screen.Main.BASE_ROUTE) {
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
                    navController.navigate(Screen.Main.BASE_ROUTE) {
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

        composable(
            route = Screen.Main.route,
            arguments = listOf(
                navArgument("tab") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            val initialTab = backStackEntry.arguments?.getInt("tab") ?: 0
            MainScreen(
                viewModel = viewModel,
                initialTab = initialTab,
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
                },
                onOrderConfirmed = {
                    navController.navigate(Screen.Main.withTab(1)) {
                        popUpTo(Screen.Checkout.route) { inclusive = true }
                    }
                },
                onBackClicked = { navController.popBackStack() },
                onAddMoreMenu = { navController.popBackStack() }
            )
        }

        composable(Screen.StatusPesanan.route) {
            StatusPesananScreen(
                currentStatus = uiState.activeOrderStatus,
                onConfirmDelivery = {
                    viewModel.confirmOrderDelivery()
                    navController.navigate(Screen.Main.BASE_ROUTE) {
                        popUpTo(Screen.StatusPesanan.route) { inclusive = true }
                    }
                },
                onBackClicked = { navController.popBackStack() }
            )
        }

        composable(Screen.Pengaturan.route) {
            PengaturanScreen(
                onBackClicked = { navController.popBackStack() },
                onNavigateToEditProfil = {
                    navController.navigate(Screen.EditProfil.route)
                }
            )
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

        composable(Screen.EditProfil.route) {
            EditProfilScreen(
                userName = uiState.userName,
                userEmail = uiState.userEmail,
                userPhone = uiState.userPhone,
                userLocation = uiState.userLocation,
                profileImageUrl = uiState.profileImageUrl,
                isUploadingImage = uiState.isUploadingProfileImage,

                onSave = { newName, newPhone, newAddress ->
                    viewModel.updateProfile(
                        newName = newName,
                        newPhone = newPhone,
                        newAddress = newAddress,
                        onSuccess = {},
                        onError = {}
                    )
                },

                onUploadImage = { imageBytes, extension ->
                    viewModel.uploadProfileImage(
                        imageBytes = imageBytes,
                        fileExtension = extension,
                        onSuccess = {},
                        onError = { errMsg ->
                            android.util.Log.e("NavGraph", "Upload foto gagal: $errMsg")
                        }
                    )
                },

                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}