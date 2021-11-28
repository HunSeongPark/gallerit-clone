package com.hunseong.gallerit_clone.data.db

import androidx.room.Database
import com.hunseong.gallerit_clone.data.model.RedditImage

// exportSchema : Room의 Schema 구조를 지정한 폴더로 export 할 것인지에 대한 여부
// DB version의 history를 기록하는 데에 유용, 그러나 test app이므로 false
@Database(entities = [RedditImage::class], version = 1, exportSchema = false)
abstract class AppDatabase {
    abstract fun redditImageDao(): RedditImageDao
}