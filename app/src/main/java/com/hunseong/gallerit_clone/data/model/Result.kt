package com.hunseong.gallerit_clone.data.model

import java.io.IOException

// declaration-site variance 형태의 제네릭 타입
// out T를 통해 타입 T의 하위 타입도 허용. 공변성(covariance) 보장
// 이를 통해 제네릭 타입의 하위 타입인 Nothing에 대한 cast 가능
sealed class Result {

    object Loading: Result()

    object Empty: Result()

    data class Success<T>(val data: T) : Result()

    data class Error(val exception: Throwable) : Result() {
        val isNetworkError: Boolean
            get() = exception is IOException
    }
}
