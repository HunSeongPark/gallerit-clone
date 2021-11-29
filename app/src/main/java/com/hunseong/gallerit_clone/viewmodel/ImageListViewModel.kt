package com.hunseong.gallerit_clone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hunseong.gallerit_clone.data.model.Result
import com.hunseong.gallerit_clone.data.repository.RedditImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(private val repository: RedditImageRepository) :
    ViewModel() {

    private val query = MutableStateFlow("")

    // debounce : 지정한 Timemillis 안에 새로운 이벤트가 발생하지 않을 시 flow 발생
    // filter : EditText에서 입력 받은 text가 빈 값이 아닐 경우 Flow 전달
    // flatMapLatest : Flow 발생 시 이전에 대기, 동작중인 Flow cancel
    // stateIn : Flow를 StateFlow로 변경 (Cold stream -> Hot stream)
    // stateIn - scope : 지정된 Scope에서 up-stream flow 시작
    // stateIn - started = WhileSubscribed(5000) : 마지막 Collector가 사라지고 upstream flow의 중지 사이 지연 시간 (화면 회전 시 중지되지 않도록)
    // stateIn - initialValue : StateFlow의 초기값. StateFlow는 초기값을 항상 가져야함
    val result: StateFlow<Result> = query
        .debounce(DEBOUNCE_MILLIS)
        .filter { text ->
            text.isNotEmpty()
        }
        .flatMapLatest { text ->
            repository.getTopImages(text)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Result.Empty
        )

    fun setQuery(text: String) {
        query.value = text
    }

    companion object {
        private const val DEBOUNCE_MILLIS = 700L
    }
}