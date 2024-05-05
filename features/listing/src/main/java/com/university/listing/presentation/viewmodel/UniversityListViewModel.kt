package com.university.listing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.university.core.exception.AppException
import com.university.core.exception.toAppException
import com.university.core.entity.University
import com.university.listing.domain.GetUniversityListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class UniversityListViewModel @Inject constructor(
    private val getUniversityListUseCase: GetUniversityListUseCase
) : ViewModel() {

    private val _screenState: MutableStateFlow<UniversityListState> by lazy {
        MutableStateFlow(UniversityListState.Initial)
    }

    internal val screenState: StateFlow<UniversityListState> = _screenState

    init {
        getUniversityList()
    }

    private fun getUniversityList() {
        _screenState.value = UniversityListState.Loading
        getUniversityListUseCase()
            .onEach { _screenState.value = UniversityListState.Success(it) }
            .catch { _screenState.value = UniversityListState.Error(it.toAppException()) }
            .launchIn(viewModelScope)
    }
}

sealed class UniversityListState {
    data object Initial : UniversityListState()
    data object Loading : UniversityListState()
    data class Success(val data: List<University>) : UniversityListState()
    data class Error(val e: AppException) : UniversityListState()
}