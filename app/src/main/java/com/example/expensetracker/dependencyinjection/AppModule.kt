package com.example.expensetracker.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.example.expensetracker.database.ApplicationDatabase
import com.example.expensetracker.database.userdatabase.UserDataDao
import com.example.expensetracker.repository.UserDataRepository
import com.example.expensetracker.viewmodel.UserDataViewmodel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ApplicationDatabase {
        return Room.databaseBuilder(
            context, ApplicationDatabase::class.java, "Application Database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(applicationDatabase: ApplicationDatabase): UserDataDao {
        return applicationDatabase.userDataDao()
    }

    @Provides
    @Singleton
    fun provideUserDataRepository(userDataDao: UserDataDao): UserDataRepository {
        return UserDataRepository(userDataDao)
    }

    @Provides
    @Singleton
    fun provideUserDataViewmodel(userDataRepository: UserDataRepository): UserDataViewmodel {
        return UserDataViewmodel(userDataRepository)
    }
}