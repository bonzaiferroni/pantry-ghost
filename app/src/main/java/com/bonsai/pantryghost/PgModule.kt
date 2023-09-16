package com.bonsai.pantryghost

import android.content.Context
import androidx.room.Room
import com.bonsai.pantryghost.data.AppDatabase
import com.bonsai.pantryghost.data.DaoRepository
import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.data.usda.UsdaDao
import com.bonsai.pantryghost.data.usda.UsdaRepository
import com.bonsai.pantryghost.data.usda.UsdaRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        return DaoRepository(
            foodDao = appDatabase.foodDao(),
            servingAmountDao = appDatabase.servingAmountDao(),
            mealTimeDao = appDatabase.mealTimeDao(),
            mealTypeDao = appDatabase.mealTypeDao(),
            servingDao = appDatabase.servingDao(),
            mealDao = appDatabase.mealDao(),
            foodTagDao = appDatabase.foodTagDao(),
            foodTagJoinDao = appDatabase.foodTagJoinDao()
        )
    }

    @Provides
    @Singleton
    fun provideUsdaRepository(): UsdaRepository {
        val baseUrl = "https://api.nal.usda.gov/fdc/v1/foods/"
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val usdaDao = retrofit.create(UsdaDao::class.java)
        return UsdaRetrofit(usdaDao)
    }
}