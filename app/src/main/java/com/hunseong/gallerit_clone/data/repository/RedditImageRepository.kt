package com.hunseong.gallerit_clone.data.repository

import com.hunseong.gallerit_clone.data.db.RedditImageDao
import com.hunseong.gallerit_clone.data.model.RedditImage
import com.hunseong.gallerit_clone.data.model.RedditImages
import com.hunseong.gallerit_clone.data.model.Result
import com.hunseong.gallerit_clone.data.network.RedditImageRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RedditImageRepository @Inject constructor(
    private val remote: RedditImageRemote,
    private val dao: RedditImageDao
) : Repository {
    fun getTopImages(subreddit: String): Flow<Result<RedditImages>> = flow {
        emit(Result.loading())
        val images = remote.getTopImages(subreddit)
        emit(Result.successOrEmpty(images))
    }.catch { e ->
        emit(Result.error(e))
    }
}