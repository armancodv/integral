package com.armanco.integral

import android.content.Context
import androidx.room.Room
import com.armanco.integral.managers.db.Db
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
    fun provideDatabase(@ApplicationContext appContext: Context): Db {
        return Room.databaseBuilder(
            appContext,
            Db::class.java,
            "database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCategoryDao(db: Db) = db.categoryDao()

    @Singleton
    @Provides
    fun provideItemDao(db: Db) = db.formulaDao()
}