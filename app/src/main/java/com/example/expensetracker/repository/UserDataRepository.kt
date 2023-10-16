package com.example.expensetracker.repository

import androidx.lifecycle.LiveData
import com.example.expensetracker.database.userdatabase.UserDataDao
import com.example.expensetracker.database.userdatabase.UserDataEntity
import javax.inject.Inject

class UserDataRepository @Inject constructor(private val userDataDao: UserDataDao) {

    val getAllUsers: LiveData<List<UserDataEntity>> = userDataDao.getAllUsers()

    suspend fun insert(userDataEntity: UserDataEntity) {
        userDataDao.insert(userDataEntity)
    }

    fun getuserName(userName: String): LiveData<UserDataEntity?> {
        return userDataDao.getUsername(userName)
    }

    suspend fun updateFlag(userName: String, flag: Boolean) {
        userDataDao.updateFlag(userName, flag)
    }
}