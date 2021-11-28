package com.hunseong.gallerit_clone.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class RedditImage(
    @PrimaryKey val id: String,
    val title: String,
    val subreddit: String,
    val url: String
): Parcelable

// List<RedditImage> Type을 RedditImages로 alias(별명) 지정
typealias RedditImages = List<RedditImage>
