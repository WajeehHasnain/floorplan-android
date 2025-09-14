package com.example.floorplan.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val length: Double,
    val width: Double,
    val area: Double,
    val createdAt: Long = System.currentTimeMillis()
)
