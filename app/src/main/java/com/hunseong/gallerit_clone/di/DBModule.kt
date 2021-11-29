package com.hunseong.gallerit_clone.di

import android.content.Context
import androidx.room.Room
import com.hunseong.gallerit_clone.data.db.AppDatabase
import com.hunseong.gallerit_clone.data.db.RedditImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DBModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "gallerit.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRedditImageDao(database: AppDatabase) : RedditImageDao {
        return database.redditImageDao()
    }
}