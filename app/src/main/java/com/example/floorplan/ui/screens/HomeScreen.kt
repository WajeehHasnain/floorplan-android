package com.example.floorplan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Text("Welcome to FloorPlan", style = MaterialTheme.typography.headlineSmall)

            Button(onClick = { /* TODO: Navigate to design screen */ }) {
                Text("Start Design")
            }

            Button(onClick = { navController.navigate(Screen.Saved.route) }) {
                Text("Saved Projects")
            }
        }
        }
    }
}
