package com.example.githublookup.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githublookup.data.models.User
import com.example.githublookup.data.repositories.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val repository: SearchRepository) :
    ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val users: StateFlow<PagingData<User>> =
        _searchQuery
            .debounce(300L)
            .filter { it.length >= 2 }
            .distinctUntilChanged()
            .flatMapLatest { query -> repository.searchUsersPaged(query) }
            .cachedIn(viewModelScope)
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily, PagingData.empty()
            )


    fun onQueryChanged(query: String) {
        _searchQuery.value = query
    }
}