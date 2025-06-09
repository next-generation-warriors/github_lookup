package com.example.githublookup.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githublookup.presentation.components.RepoItem
import com.example.githublookup.presentation.components.UserHeader
import com.example.githublookup.presentation.viewmodels.ProfileScreenViewModel
import com.example.githublookup.ui.theme.GithubLookupTheme
@Composable
fun ProfileScreen(
    userName: String,
    viewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val user by viewModel.user
    val loading by viewModel.loading
    val error by viewModel.error
    val repoItems = viewModel.repos.collectAsLazyPagingItems()

    LaunchedEffect(userName) {
        viewModel.loadUser(userName)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            error != null -> {
                Text(
                    text = "Error: $error",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            user != null -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        UserHeader(user = user!!)
                    }

                    items(repoItems.itemCount) { index ->
                        repoItems[index]?.let {
                            RepoItem(repo = it)
                        }
                    }

                    repoItems.apply {
                        when {
                            loadState.append is androidx.paging.LoadState.Loading -> {
                                item {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                            .wrapContentWidth(Alignment.CenterHorizontally)
                                    )
                                }
                            }

                            loadState.append is androidx.paging.LoadState.Error -> {
                                val errorState = loadState.append as androidx.paging.LoadState.Error
                                item {
                                    Text(
                                        text = "Error loading more repos: ${errorState.error.message}",
                                        color = MaterialTheme.colorScheme.error,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowProfileScreen() {
    GithubLookupTheme {
        ProfileScreen("")
    }
}