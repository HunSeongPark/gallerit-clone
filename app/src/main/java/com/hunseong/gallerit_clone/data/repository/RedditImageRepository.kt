package com.hunseong.gallerit_clone.data.repository

import com.hunseong.gallerit_clone.data.db.RedditImageDao
import com.hunseong.gallerit_clone.data.model.RedditImage
import com.hunseong.gallerit_clone.data.model.Result
import com.hunseong.gallerit_clone.data.network.RedditImageRemote
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class RedditImageRepository @Inject constructor(
    private val remote: RedditImageRemote,
    private val dao: RedditImageDao,
) : Repository {

    fun getTopImages(
        subreddit: String,
    ): Flow<Result> = flow {
        emit(Result.Loading)
        val images = remote.getTopImages(subreddit)
        if (images.isEmpty()) {
            emit(Result.Empty)
        } else {
            emit(Result.Success(images))
        }
    }.catch { e ->
        emit(Result.Error(e))
    }

    fun isFavorite(id: String) = flow {
        emit(dao.isImageExists(id))
    }


    suspend fun addFavorite(image: RedditImage) {
        dao.insertImage(image)
    }

    suspend fun removeFavorite(image: RedditImage) {
        dao.deleteImage(image)
    }

    fun getFavorites(): Flow<Result> = flow {
        emit(Result.Loading)
        dao.getAllImages().collect {
            if (it.isEmpty()) {
                emit(Result.Empty)
            } else {
                emit(Result.Success(it))
            }
        }
    }
}