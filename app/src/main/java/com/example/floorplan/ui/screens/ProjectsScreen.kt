package com.example.floorplan.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.floorplan.data.local.ProjectEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectScreen(viewModel: ProjectViewModel) {
    val projects by viewModel.projects.collectAsState()

    var projectName by remember { mutableStateOf("") }
    var length by remember { mutableStateOf("") }
    var width by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Saved Projects") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Inputs
            OutlinedTextField(
                value = projectName,
                onValueChange = { projectName = it },
                label = { Text("Project Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = length,
                onValueChange = { length = it },
                label = { Text("Length (m)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = width,
                onValueChange = { width = it },
                label = { Text("Width (m)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {
                    val l = length.toDoubleOrNull()
                    val w = width.toDoubleOrNull()
                    if (projectName.isNotBlank() && l != null && w != null) {
                        viewModel.addProject(projectName, l, w)
                        projectName = ""
                        length = ""
                        width = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Project")
            }

            Spacer(Modifier.height(16.dp))

            // List
            LazyColumn {
                items(projects) { project ->
                    ProjectItem(
                        project = project,
                        onDelete = { viewModel.deleteProject(project) }
                    )
                }
            }
        }
    }
}

@Composable
fun ProjectItem(project: ProjectEntity, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = project.name, style = MaterialTheme.typography.titleMedium)
            Text("Length: ${project.length} m")
            Text("Width: ${project.width} m")
            Text("Area: ${project.area} m²")
            Spacer(Modifier.height(8.dp))
            Button(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}
