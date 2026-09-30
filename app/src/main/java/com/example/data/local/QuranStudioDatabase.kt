package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProjectEntity::class], version = 1, exportSchema = false)
abstract class QuranStudioDatabase : RoomDatabase() {
  abstract fun projectDao(): ProjectDao

  companion object {
    @Volatile
    private var INSTANCE: QuranStudioDatabase? = null

    fun getDatabase(context: Context): QuranStudioDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          QuranStudioDatabase::class.java,
          "quran_studio_db"
        ).fallbackToDestructiveMigration().build()
        INSTANCE = instance
        instance
      }
    }
  }
}
