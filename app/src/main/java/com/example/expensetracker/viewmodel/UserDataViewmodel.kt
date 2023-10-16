package com.example.expensetracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.database.userdatabase.UserDataEntity
import com.example.expensetracker.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDataViewmodel @Inject constructor(private val userDataRepository: UserDataRepository) :
    ViewModel() {

    val userList: LiveData<List<UserDataEntity>> = userDataRepository.getAllUsers


    fun insertUser(userDataEntity: UserDataEntity) {
        viewModelScope.launch(Dispatchers.Default) {
            userDataRepository.insert(userDataEntity)
        }
    }

    fun getUserName(userName: String): LiveData<UserDataEntity?> {
        return userDataRepository.getuserName(userName)
    }

    fun updateFlag(userName: String, flag: Boolean) {
        viewModelScope.launch(Dispatchers.Default) {
            userDataRepository.updateFlag(userName, flag)
        }
    }

}