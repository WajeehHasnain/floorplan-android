package com.example.floorplan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.floorplan.ui.ProjectScreen
import com.example.floorplan.ui.ProjectViewModel
import com.example.floorplan.ui.theme.FloorPlanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FloorPlanTheme {
                val viewModel: ProjectViewModel = viewModel()
                ProjectScreen(viewModel)
            }
        }
    }
}
