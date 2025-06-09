package com.example.githublookup.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.example.githublookup.presentation.components.UserCard
import com.example.githublookup.presentation.viewmodels.SearchScreenViewModel

@Composable
fun SearchScreen(viewModel: SearchScreenViewModel = hiltViewModel(), onUserClick: (String) -> Unit) {
    val users = viewModel.users.collectAsLazyPagingItems()
    val query by viewModel.searchQuery.collectAsState()

    Column(modifier = Modifier.padding(all = 16.dp)) {

        OutlinedTextField(
            value = query,
            onValueChange = viewModel::onQueryChanged,
            label = { Text("Search Github Users") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(users.itemCount) { index ->
                val user = users[index]
                user?.let {
                    UserCard(it) {
                        onUserClick(it.login)
                    }
                }
            }

            users.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally)) }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { CircularProgressIndicator(Modifier.padding(8.dp)) }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = loadState.refresh as LoadState.Error
                        item { Text("Error: ${error.error.message}") }
                    }
                }
            }
        }
    }
}