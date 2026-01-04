package com.geekhaven.app.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val stats by viewModel.stats.collectAsState()

    // Removed fillMaxSize to allow embedding
    Column(modifier = Modifier.fillMaxWidth()) {
        // Text(text = "Reading DNA", style = MaterialTheme.typography.headlineMedium) // Removed header as parent handles it
        // Spacer(modifier = Modifier.height(24.dp))
        
        StatRow("Total Books", stats.totalBooks.toString())
        StatRow("Completed", stats.completedBooks.toString())
        StatRow("Pages Read", stats.pagesRead.toString())
        StatRow("Hours Listened", stats.hoursListened.toString())
    }
}

@Composable
fun StatRow(label: String, value: String) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Text(text = value, style = MaterialTheme.typography.bodyLarge, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
    }
}
