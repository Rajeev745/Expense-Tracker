package com.example.expensetracker.database.userdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDataDao {

    @Insert
    suspend fun insert(userDataEntity: UserDataEntity)

    @Query("SELECT * FROM Register_users_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<UserDataEntity>>

    @Query("SELECT * FROM Register_users_table WHERE userName LIKE :userName")
    fun getUsername(userName: String): LiveData<UserDataEntity?>

    @Query("SELECT isLoggedIn FROM Register_users_table WHERE userName LIKE :userName")
    suspend fun getUserState(userName: String): Boolean

    @Query("SELECT * FROM Register_users_table WHERE userName LIKE :userName")
    suspend fun getUserPassword(userName: String): UserDataEntity?

    @Query("UPDATE Register_users_table SET age = :newAge WHERE username = :username")
    suspend fun updateAge(username: String, newAge: Int)

    @Query("UPDATE Register_users_table SET mobile = :newMobile WHERE username = :username")
    suspend fun updateMobile(username: String, newMobile: String)

    @Query("UPDATE Register_users_table SET password = :newPassword WHERE username = :username")
    suspend fun updatePassword(username: String, newPassword: String)

    @Query("UPDATE Register_users_table SET budget = :newBudget WHERE username = :username")
    suspend fun updateBudget(username: String, newBudget: Int)

    @Query("UPDATE Register_users_table SET isLoggedIn = :flag WHERE username = :username")
    suspend fun updateFlag(username: String, flag: Boolean)
}