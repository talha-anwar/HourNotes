@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.hournotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hournotes.ui.theme.HourNotesTheme
import kotlin.random.Random

// Sample data with image info
data class PinItem(
    val id: Int,
    val title: String,
    val color: Color,
    val aspectRatio: Float // Will help simulate images of different heights
)

// Create dummy pin items with varying heights
val dummyPins = List(30) { index ->
    val colorOptions = listOf(
        Color(0xFFE57373), // Red
        Color(0xFF81C784), // Green
        Color(0xFF64B5F6), // Blue
        Color(0xFFFFD54F), // Yellow
        Color(0xFFBA68C8)  // Purple
    )

    // Random aspect ratio between 0.6 (portrait) and 1.5 (landscape)
    val aspectRatio = (Random.nextFloat() * 0.9f) + 0.6f

    PinItem(
        id = index,
        title = "Pin #$index",
        color = colorOptions[index % colorOptions.size],
        aspectRatio = aspectRatio
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HourNotesTheme {
                SimpleAppScreen()
            }
        }
    }
}

@Composable
fun AddButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(Icons.Filled.Add, "Add new pin")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleAppScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text("HourNotes", style = MaterialTheme.typography.titleLarge)
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(end = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { /* Settings action */ }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                tint = MaterialTheme.colorScheme.onSecondary
                            )
                        }
                    }
                },
                modifier = Modifier.height(80.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    titleContentColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        },
        floatingActionButton = {
            AddButton(onClick = { /* Handle button click here */ })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            PinterestGrid()
        }
    }
}

@Composable
fun PinterestGrid() {
    // Use the LazyVerticalStaggeredGrid from Jetpack Compose
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2), // You can adjust number of columns
        contentPadding = PaddingValues(4.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(dummyPins) { pin ->
            PinCard(pin)
        }
    }
}

@Composable
fun PinCard(pin: PinItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = pin.color.copy(alpha = 0.8f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            // This box represents the image with the pin's aspect ratio
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(pin.aspectRatio) // Use our simulated aspect ratio
                    .background(pin.color)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                contentAlignment = Alignment.Center
            ) {
                // In a real app, you would place an actual image here
                Text(
                    text = "Image ${pin.id}",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Pin title/caption
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = pin.title,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
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