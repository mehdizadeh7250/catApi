package com.mehdizadeh.catfeed.presentation.screen.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.mehdizadeh.catfeed.domain.model.CatBreed
import com.mehdizadeh.catfeed.navigation.manager.LocalNavigationManager
import com.mehdizadeh.catfeed.navigation.screen.AppScreens
import com.mehdizadeh.catfeed.presentation.screen.home.viewModel.HomeViewActions
import com.mehdizadeh.catfeed.presentation.screen.home.viewModel.HomeViewModel
import com.mehdizadeh.catfeed.ui.theme.AppTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.viewState.collectAsState()
    val listState = rememberLazyListState()
    val navigationManager = LocalNavigationManager.current
    val context = LocalContext.current
    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
        }.distinctUntilChanged().filter { last ->
            last >= listState.layoutInfo.totalItemsCount - 3
        }.collect {
            if (!state.value.isLoadingMore && !state.value.endReached) {
                viewModel.handle(HomeViewActions.GetBreeds(limit = 10, page = state.value.page + 1))
            }
        }
    }
    PullToRefreshBox(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colorScheme.onPrimary),
        isRefreshing = state.value.isRefreshing,
        onRefresh = {
            viewModel.handle(HomeViewActions.GetBreeds(limit = 10, page = 0))
        }
    ) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.value.catBreeds, key = { it.id }) { breed ->
                CatBreedCard(breed) {
                    navigationManager.navigate(AppScreens.DetailScreen)
                    navigationManager.setCurrentScreenData("breed", breed)
                }
            }
            if (state.value.isLoadingMore) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

    if (state.value.isLoading && state.value.catBreeds.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    state.value.error?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun CatBreedCard(breed: CatBreed, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation()
    ) {
        Row(Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(breed.imageUrl ?: ""),
                contentDescription = breed.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(text = breed.name)
                Text(text = breed.origin ?: "Unknown")
                Text(text = breed.description ?: "")
            }
        }
    }
}