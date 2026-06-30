package com.example.test.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.test.ui.theme.PrimaryOrange
import com.example.test.ui.theme.PrimaryRed
import com.example.test.ui.viewmodel.OrderViewModel
import com.example.test.ui.viewmodel.CartItem
import com.example.test.ui.navigation.Screen

@Composable
fun MainScreen(
    viewModel: OrderViewModel,
    onNavigateToCheckout: () -> Unit,
    onNavigateToSettings: (String) -> Unit, // screen route
    onNavigateToStatusPesanan: () -> Unit,
    initialTab: Int = 0,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    // Tab awal mengikuti initialTab (misal dikirim dari KonfirmasiScreen agar langsung buka tab Menu)
    var selectedTab by rememberSaveable(initialTab) { mutableIntStateOf(initialTab) }
    var searchQuery by rememberSaveable { mutableStateOf("") }
 
    val tabs = listOf(
        TabItem("Beranda", Icons.Default.Home),
        TabItem("Menu", Icons.Default.Restaurant),
        TabItem("Pesanan", Icons.Default.Receipt),
        TabItem("Profil", Icons.Default.Person)
    )
 
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = { Icon(tab.icon, contentDescription = tab.title) },
                        label = { Text(tab.title) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryRed,
                            selectedTextColor = PrimaryRed,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = PrimaryOrange.copy(alpha = 0.2f)
                        )
                    )
                }
            }
        },
        modifier = modifier
    ) { paddingValues ->
        val innerModifier = Modifier.padding(paddingValues)
        when (selectedTab) {
            0 -> BerandaScreen(
                userName = uiState.userName,
                userLocation = uiState.userLocation,
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                onMenuTabSelected = { selectedTab = 1 },
                modifier = innerModifier
            )
            1 -> MenuScreen(
                userName = uiState.userName,
                menuByCategory = viewModel.menuByCategory,
                cartItems = uiState.cartItems,
                onAddToCart = { menuItem ->
                    viewModel.addToCart(menuItem)
                },
                onCheckoutClicked = onNavigateToCheckout,
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                modifier = innerModifier
            )
            2 -> PesananScreen(
                activeStatus = uiState.activeOrderStatus,
                onTrackOrder = onNavigateToStatusPesanan,
                modifier = innerModifier
            )
            3 -> ProfilScreen(
                userName = uiState.userName,
                userEmail = uiState.userEmail,
                onNavigateToEditProfil = { onNavigateToSettings(Screen.EditProfil.route) },
                onNavigateToSettings = onNavigateToSettings,
                modifier = innerModifier
            )
        }
    }
}

private data class TabItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)