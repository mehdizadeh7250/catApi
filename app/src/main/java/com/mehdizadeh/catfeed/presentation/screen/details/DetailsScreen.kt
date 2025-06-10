package com.mehdizadeh.catfeed.presentation.screen.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mehdizadeh.catfeed.domain.model.CatBreed
import com.mehdizadeh.catfeed.navigation.manager.LocalNavigationManager
import com.mehdizadeh.catfeed.utils.openWebPage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen() {
    val navigationManager = LocalNavigationManager.current
    val context = LocalContext.current
    val catBreed =
        navigationManager.getCurrentScreenFlowData("breed", CatBreed())?.collectAsState()?.value
    catBreed?.let { breed ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = breed.name) },
                    navigationIcon = {
                        IconButton(onClick = { navigationManager.navigateBack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    AsyncImage(
                        model = breed.imageUrl,
                        contentDescription = "Cat Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }

                item { Text("Origin: ${breed.origin}") }
                item { Text("Temperament: ${breed.temperament}") }
                item { Text("Life Span: ${breed.lifeSpan} years") }
                item { Text("Description: ${breed.description}") }
                item { Text("Dog Friendly: ${breed.dogFriendly}") }
                item { Text("Child Friendly: ${breed.childFriendly}") }
                item { Text("Energy Level: ${breed.energyLevel}") }
                item {
                    Text("Wikipedia Url: ${breed.wikipediaUrl}", modifier = Modifier.clickable {
                        breed.wikipediaUrl?.let { context.openWebPage(it) }
                    })
                }
            }
        }
    } ?: Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}