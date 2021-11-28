package com.hunseong.gallerit_clone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hunseong.gallerit_clone.data.model.RedditImages
import com.hunseong.gallerit_clone.data.model.Result
import com.hunseong.gallerit_clone.data.repository.RedditImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

class ImageListViewModel(private val repository: RedditImageRepository) : ViewModel() {

    private val query = MutableStateFlow("")

    val images: LiveData<Result<RedditImages>> = query
        .debounce(DEBOUNCE_MILLIS)
        .filter { text ->
            text.isNotEmpty()
        }
        .flatMapLatest { text ->
            repository.getTopImages(text)
        }
        .asLiveData()

    fun setQuery(text: String) {
        query.value = text
    }

    companion object {
        private const val DEBOUNCE_MILLIS = 700L
    }
}