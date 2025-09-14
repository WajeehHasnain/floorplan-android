package com.example.floorplan.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.floorplan.data.local.AppDatabase
import com.example.floorplan.data.local.ProjectEntity
import com.example.floorplan.data.local.ProjectRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ProjectViewModel - placed in:
 * app/src/main/java/com/example/floorplan/viewmodel/ProjectViewModel.kt
 *
 * This uses the repository in: com.example.floorplan.data.local.ProjectRepository
 */
class ProjectViewModel(private val app: Application) : AndroidViewModel(app) {

    // instantiate repository using the AppDatabase singleton
    private val repository: ProjectRepository

    // Expose projects as a StateFlow so Compose can collect it without needing an "initial" value
    private val _projects: StateFlow<List<ProjectEntity>>

    val projects: StateFlow<List<ProjectEntity>>
        get() = _projects

    init {
        val dao = AppDatabase.getDatabase(app).projectDao()
        repository = ProjectRepository(dao)

        _projects = repository.getAllProjects()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }

    fun addProject(name: String, length: Double, width: Double) {
        val area = length * width
        viewModelScope.launch {
            repository.insertProject(
                ProjectEntity(
                    name = name,
                    length = length,
                    width = width,
                    area = area
                )
            )
        }
    }

    fun deleteProject(project: ProjectEntity) {
        viewModelScope.launch {
            repository.deleteProject(project)
        }
    }

    /**
     * Factory for creating this ViewModel from Compose:
     * Usage in Compose: viewModel(factory = ProjectViewModel.Factory(app))
     */
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProjectViewModel::class.java)) {
                return ProjectViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
