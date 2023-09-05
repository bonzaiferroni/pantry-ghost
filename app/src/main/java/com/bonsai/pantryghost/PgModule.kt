package com.bonsai.pantryghost

import android.content.Context
import androidx.room.Room
import com.bonsai.pantryghost.data.AppDatabase
import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.data.DataRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PgModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "pantry-ghost"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDataRepository(appDatabase: AppDatabase): DataRepository {
        return DataRepositoryImp(
            foodDao = appDatabase.foodDao(),
            ingredientDao = appDatabase.ingredientDao(),
            mealDao = appDatabase.mealDao(),
            mealTypeDao = appDatabase.mealTypeDao()
        )
    }
}