@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.hournotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hournotes.ui.theme.HourNotesTheme
import kotlin.random.Random

// Sample data with image info
data class PinItem(
    val id: Int,
    val title: String,
    val imageResIdRes: Int,
    val aspectRatio: Float, // Will help simulate images of different heights
    val imageRe: Int
)
val imageIds = listOf(
    R.drawable.cr,
    R.drawable.nigga,
    R.drawable.no_bitches,
    R.drawable.oh_maa_gahh,
    R.drawable.quandale_dingle,
    R.drawable.prem,
    R.drawable.massive_rat,
    R.drawable.meat_bob,
    R.drawable.sassy_bee,
    R.drawable.sassy_car_2,
    R.drawable.sassy_car,
    R.drawable.wator_melon,
    R.drawable.hehe
)
// Create dummy pin items with varying heights
val dummyPins = List(30) { index ->
    val aspectRatio = (Random.nextFloat() * 0.9f) + 0.6f
    PinItem(
        id = index,
        title = "Pin #$index",
        imageResIdRes = imageIds[index % imageIds.size],
        aspectRatio = aspectRatio,
        imageRe = imageIds[index % imageIds.size]
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
                modifier = Modifier.height(60.dp),
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
        contentPadding = PaddingValues(8.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
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
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Image(
                painter = painterResource(id = pin.imageResIdRes),
                contentDescription = "Pin Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(pin.aspectRatio) // height adjusts to match image
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = pin.title,
                    modifier = Modifier.padding(8.dp),
                    color = MaterialTheme.colorScheme.onSurface,
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