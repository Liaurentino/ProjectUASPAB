package com.example.test

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.test.ui.navigation.AppNavGraph
import com.example.test.ui.theme.AppTheme
import com.example.test.ui.viewmodel.OrderViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Custom Uncaught Exception Handler to capture all background/foreground runtime errors
        val originalHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("DineInRestoCrash", "CRASH in thread ${thread.name}", throwable)
            originalHandler?.uncaughtException(thread, throwable)
        }

        try {
            setContent {
                AppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
                        val viewModel: OrderViewModel = viewModel()
                        AppNavGraph(navController = navController, viewModel = viewModel)
                    }
                }
            }
        } catch (t: Throwable) {
            Log.e("DineInRestoCrash", "Error during onCreate/setContent", t)
        }
    }
}