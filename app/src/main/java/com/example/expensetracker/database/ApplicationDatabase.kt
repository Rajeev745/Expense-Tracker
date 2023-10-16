package com.example.expensetracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.expensetracker.database.userdatabase.UserDataDao
import com.example.expensetracker.database.userdatabase.UserDataEntity

@Database(entities = [UserDataEntity::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun userDataDao(): UserDataDao
}