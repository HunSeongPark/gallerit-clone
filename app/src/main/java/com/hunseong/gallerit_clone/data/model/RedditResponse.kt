package com.hunseong.gallerit_clone.data.model

import com.google.gson.annotations.SerializedName

data class RedditResponse(
    @SerializedName("data") val data: RedditData
)

data class RedditData(
    @SerializedName("children") val children: List<RedditChild>
)

data class RedditChild(
    @SerializedName("data") val post: RedditPost
)

data class RedditPost(
    @SerializedName("name") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("subreddit") val subreddit: String,
    @SerializedName("post_hint") val postHint: String,
    @SerializedName("url") val url: String
)