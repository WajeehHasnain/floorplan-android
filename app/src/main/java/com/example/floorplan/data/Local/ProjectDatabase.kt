package com.example.floorplan.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.floorplan.data.Local.ProjectDao

@Database(entities = [ProjectEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "project_db"
                )
                    .fallbackToDestructiveMigration() // reset on schema change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
