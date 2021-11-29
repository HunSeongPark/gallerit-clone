package com.hunseong.gallerit_clone.di

import com.hunseong.gallerit_clone.BuildConfig
import com.hunseong.gallerit_clone.data.network.RedditImageRemote
import com.hunseong.gallerit_clone.data.network.RedditImageRemoteImpl
import com.hunseong.gallerit_clone.data.network.RedditService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Singleton
    @Provides
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.reddit.com/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideRedditService(retrofit: Retrofit): RedditService {
        return retrofit.create(RedditService::class.java)
    }
}

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteModule {
    @Singleton
    @Binds
    abstract fun provideRedditImageRemoteImpl(impl: RedditImageRemoteImpl) : RedditImageRemote
}