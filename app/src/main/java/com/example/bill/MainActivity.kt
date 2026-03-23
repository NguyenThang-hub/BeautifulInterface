package com.example.bill

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CupcakeApp()
        }
    }
}

// ---------------- NAVIGATION ----------------

enum class CupcakeScreen {
    Start,
    Flavor,
    Pickup,
    Summary
}

@Composable
fun CupcakeApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CupcakeScreen.Start.name
    ) {

        composable(CupcakeScreen.Start.name) {
            StartScreen(
                onNext = {
                    navController.navigate(CupcakeScreen.Flavor.name)
                }
            )
        }

        composable(CupcakeScreen.Flavor.name) {
            FlavorScreen(
                onNext = {
                    navController.navigate(CupcakeScreen.Pickup.name)
                }
            )
        }

        composable(CupcakeScreen.Pickup.name) {
            PickupScreen(
                onNext = {
                    navController.navigate(CupcakeScreen.Summary.name)
                }
            )
        }

        composable(CupcakeScreen.Summary.name) {
            SummaryScreen(
                onCancel = {
                    navController.popBackStack(
                        CupcakeScreen.Start.name,
                        inclusive = false
                    )
                }
            )
        }
    }
}

// ---------------- UI SCREENS ----------------

@Composable
fun StartScreen(onNext: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onNext) {
            Text("Start Order")
        }
    }
}

@Composable
fun FlavorScreen(onNext: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onNext) {
            Text("Choose Flavor → Next")
        }
    }
}

@Composable
fun PickupScreen(onNext: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onNext) {
            Text("Pick Date → Next")
        }
    }
}

@Composable
fun SummaryScreen(onCancel: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Order Summary", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onCancel) {
            Text("Cancel Order")
        }
    }
}