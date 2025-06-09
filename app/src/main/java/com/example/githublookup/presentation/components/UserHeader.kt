package com.example.githublookup.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.githublookup.data.models.User
import com.example.githublookup.data.models.sampleUser
import com.example.githublookup.ui.theme.GithubLookupTheme
import com.example.githublookup.ui.theme.IceBlue

@Composable
fun UserHeader(user: User) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(IceBlue)
    ) {
        AsyncImage(
            model = user.avatarUrl,
            placeholder = rememberVectorPainter(Icons.Default.Person),
            error = rememberVectorPainter(Icons.Default.Person),
            contentDescription = "User Avatar",
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .weight(0.4f)
                .padding(16.dp)
        )

        Column(
            modifier = Modifier
                .weight(0.6f)
                .padding(16.dp)
        ) {
            Text(text = user.name ?: user.login, style = MaterialTheme.typography.titleLarge)
            Text(text = user.bio ?: "No bio", style = MaterialTheme.typography.bodyMedium)
            Text(text = "${user.followers} followers", style = MaterialTheme.typography.bodySmall)
            Text(
                text = "${user.publicRepos} repositories",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
fun ShowUSerHeader() {
    GithubLookupTheme {
        UserHeader(sampleUser)
    }
}