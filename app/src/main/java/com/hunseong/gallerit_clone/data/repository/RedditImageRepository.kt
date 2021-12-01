package com.hunseong.gallerit_clone.data.repository

import com.hunseong.gallerit_clone.data.db.RedditImageDao
import com.hunseong.gallerit_clone.data.model.RedditImage
import com.hunseong.gallerit_clone.data.model.Result
import com.hunseong.gallerit_clone.data.network.RedditImageRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
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
        } else emit(Result.Success(images))
    }.catch { e ->
        emit(Result.Error(e))
    }

    fun isFavorite(id: String?) = flow {
        id?.let { emit(dao.isImageExists(id)) }
    }

    suspend fun addFavorite(image: RedditImage) {
        dao.insertImage(image)
    }

    suspend fun removeFavorite(image: RedditImage) {
        dao.deleteImage(image)
    }
}