package com.hunseong.gallerit_clone.data.network

import com.hunseong.gallerit_clone.data.model.RedditResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditService {

    @GET("r/{subreddit}/top.json?raw_json=1")
    suspend fun getTopPosts(
        @Path("subreddit") subreddit: String
    ): RedditResponse
}