package com.example.floorplan.data.local

import com.example.floorplan.data.Local.ProjectDao
import kotlinx.coroutines.flow.Flow

class ProjectRepository(private val dao: ProjectDao) {

    fun getAllProjects(): Flow<List<ProjectEntity>> = dao.getAllProjects()

    suspend fun insertProject(project: ProjectEntity) {
        dao.insertProject(project)
    }

    suspend fun deleteProject(project: ProjectEntity) {
        dao.deleteProject(project)
    }
}
