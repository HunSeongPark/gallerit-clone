package com.hunseong.gallerit_clone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hunseong.gallerit_clone.data.model.Result
import com.hunseong.gallerit_clone.data.repository.RedditImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: RedditImageRepository) : ViewModel() {

    val favoriteImages: StateFlow<Result> = repository.getFavorites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Result.Loading
        )

}