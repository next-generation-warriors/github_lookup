package com.example.githublookup.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githublookup.data.models.Repo
import com.example.githublookup.data.models.User
import com.example.githublookup.data.repositories.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(private val repository: ProfileRepository) :
    ViewModel() {
    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> = _user

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    private val _currentUsername = mutableStateOf<String?>(null)
    private val _usernameFlow = MutableStateFlow<String?>(null)

    val repos: Flow<PagingData<Repo>> = _usernameFlow
        .filterNotNull()
        .distinctUntilChanged()
        .flatMapLatest { username ->
            repository.getUserReposPager(username)
        }
        .cachedIn(viewModelScope)

    fun loadUser(username: String) {
        if (_currentUsername.value == username) return

        _currentUsername.value = username
        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            val result = repository.getUserDetails(username)
            result.onSuccess {
                _user.value = it
                _usernameFlow.value = username
            }.onFailure {
                _error.value = it.message
            }
            _loading.value = false
        }
    }
}