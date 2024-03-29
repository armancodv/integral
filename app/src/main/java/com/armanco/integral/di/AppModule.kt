package com.armanco.integral.di

import android.content.Context
import androidx.room.Room
import com.armanco.integral.data.datastore.DataStoreManager
import com.armanco.integral.data.db.Db
import com.armanco.integral.utils.facade.EventFacade
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun dataStoreManager(@ApplicationContext appContext: Context): DataStoreManager =
        DataStoreManager(appContext)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): Db {
        return Room.databaseBuilder(
            appContext,
            Db::class.java,
            "database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideCategoryDao(db: Db) = db.categoryDao()

    @Singleton
    @Provides
    fun provideItemDao(db: Db) = db.formulaDao()

    @Singleton
    @Provides
    fun provideEventFacade() = EventFacade()

    @Singleton
    @Provides
    fun provideRemoteConfig(): FirebaseRemoteConfig {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        return remoteConfig
    }

}