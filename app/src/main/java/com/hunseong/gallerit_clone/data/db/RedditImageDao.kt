package com.hunseong.gallerit_clone.data.db

import androidx.room.*
import com.hunseong.gallerit_clone.data.model.RedditImage
import com.hunseong.gallerit_clone.data.model.RedditImages
import kotlinx.coroutines.flow.Flow

@Dao
interface RedditImageDao {

    // flow가 coroutine builder이므로 suspend keyword를 붙이지 않아도 됨
    // 데이터 처리는 비동기적으로 수행됨
    @Query("SELECT * FROM RedditImage")
    fun getAllImages(): Flow<RedditImages>

    // vararg : 가변 인자, 인자의 개수가 가변적임
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(vararg images: RedditImage)

    @Delete
    suspend fun deleteImage(vararg images: RedditImage)

    // EXISTS(...) : RedditImage Table에서 해당 id에 해당하는 값이 존재하는지 여부
    @Query("SELECT EXISTS(SELECT 1 FROM RedditImage WHERE id =:id LIMIT 1)")
    suspend fun isImageExists(id: String): Boolean
}