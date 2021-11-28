package com.hunseong.gallerit_clone.data.network

import com.hunseong.gallerit_clone.data.model.RedditImages

interface RedditImageRemote {
    suspend fun getTopImages(subreddit: String): RedditImages
}