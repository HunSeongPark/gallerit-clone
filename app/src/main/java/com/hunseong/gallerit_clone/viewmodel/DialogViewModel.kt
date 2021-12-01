package com.hunseong.gallerit_clone.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hunseong.gallerit_clone.data.model.RedditImage
import com.hunseong.gallerit_clone.data.repository.RedditImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DialogViewModel @Inject constructor(
    state: SavedStateHandle,
    private val repository: RedditImageRepository,
) : ViewModel() {

    private val image: RedditImage = state.get<RedditImage>("image")!!

    val isFavorite: StateFlow<Boolean> = repository.isFavorite(image.id)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun saveImage() {
        viewModelScope.launch {
            repository.addFavorite(image)
        }
    }

    fun removeImage() {
        viewModelScope.launch {
            repository.removeFavorite(image)
        }
    }
}