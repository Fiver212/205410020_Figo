package com.kotlin.absensi.database

import androidx.room.Database
import com.kotlin.absensi.model.ModelDatabase
import androidx.room.RoomDatabase
import com.kotlin.absensi.database.dao.DatabaseDao
// membuat class abstract untuk DatabaseDao
@Database(entities = [ModelDatabase::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao?
}