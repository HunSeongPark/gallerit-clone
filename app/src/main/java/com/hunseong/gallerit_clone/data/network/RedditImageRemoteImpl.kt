package com.hunseong.gallerit_clone.data.network

import com.hunseong.gallerit_clone.data.model.RedditImage
import com.hunseong.gallerit_clone.data.model.RedditImages
import com.hunseong.gallerit_clone.data.model.RedditPost
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RedditImageRemoteImpl @Inject constructor(private val service: RedditService) : RedditImageRemote {


    // Room, Retrofit의 경우 suspend function에 대한 main-safe를 지원
    // Dispatchers.IO가 아닌 Room 자체 Executor를 통한 coroutine, Retrofit enqueue를 통해 비동기 처리
    override suspend fun getTopImages(subreddit: String): RedditImages {
        val response = service.getTopPosts(subreddit)
        val children = response.data.children

        // null이 아닌 요소만 collection에 저장하여 반환
        return children.mapNotNull { it.post.toImage() }
    }


    // post 형태가 image인 것만 return, image가 아닐 시 null return하여 mapNotNull을 통해 필터링
    private fun RedditPost.toImage(): RedditImage? {
        return if (postHint == "image") {
            RedditImage(id, title, subreddit, url)
        } else {
            null
        }
    }
}