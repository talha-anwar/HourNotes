package com.example.hournotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hournotes.ui.theme.HourNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HourNotesTheme {
                // Just displaying the button
                SimpleAppScreen()
            }
        }
    }
}

@Composable
fun SmallExample(onClick: () -> Unit) {
    SmallFloatingActionButton(
        onClick = { onClick() },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(Icons.Filled.Add, "Small floating action button.")
    }
}

@Composable
fun SimpleAppScreen() {
    Scaffold(
        floatingActionButton = {
            SmallExample(onClick = { /* Handle button click here */ })
        }
    ) { paddingValues ->
        // Use paddingValues to add padding to the content if needed
        // Here we will add it to the modifier of the Column or Box
        Box(
            modifier = Modifier
                .padding(paddingValues) // Apply padding here
                .fillMaxSize()
        ) {
            // No additional content, just the button in the center
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleAppScreenPreview() {
    HourNotesTheme {
        SimpleAppScreen()
    }
}


