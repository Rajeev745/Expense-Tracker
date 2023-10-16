package com.example.expensetracker.database.userdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Register_users_table")
data class UserDataEntity(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val fullName: String,
    val userName: String,
    val password: String,
    val age: String?,
    val budget: String?,
    val mobile: String?,
    val isLoggedIn: Boolean = false
)