package com.example.githublookup.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.githublookup.data.models.User
import com.example.githublookup.ui.theme.IceBlue

@Composable
fun UserCard(user: User, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = IceBlue)
    ) {
        Row(Modifier.fillMaxSize()) {
            AsyncImage(
                model = user.avatarUrl,
                placeholder = rememberVectorPainter(Icons.Default.Person),
                error = rememberVectorPainter(Icons.Default.Person),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.4f)
                    .padding(16.dp)
            )


            Box(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = user.login,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}