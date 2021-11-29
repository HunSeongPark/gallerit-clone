package com.hunseong.gallerit_clone.di

import com.hunseong.gallerit_clone.data.repository.RedditImageRepository
import com.hunseong.gallerit_clone.data.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRedditImageRepository(impl: RedditImageRepository) : Repository
}