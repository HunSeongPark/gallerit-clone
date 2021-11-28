package com.hunseong.gallerit_clone.data.model

import java.io.IOException

// declaration-site variance 형태의 제네릭 타입
// out T를 통해 타입 T의 하위 타입도 허용. 공변성(covariance) 보장
// 이를 통해 제네릭 타입의 하위 타입인 Nothing에 대한 cast 가능
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()

    data class Error(val exception: Throwable) : Result<Nothing>() {
        val isNetworkError: Boolean
            get() = exception is IOException
    }

    object Empty: Result<Nothing>()

    object Loading: Result<Nothing>()

    companion object {
        fun <T> success(data: T) = Success(data)

        fun error(exception: Throwable) = Error(exception)

        fun empty() = Empty

        fun loading() = Loading

        // covariance가 보장되므로 Result<Nothing>은 Result<List<RedditImage>>의 하위 타입임이 보장
        fun <T> successOrEmpty(list: List<T>): Result<List<T>> {
            return if (list.isEmpty()) Empty else Success(list)
        }
    }
}
