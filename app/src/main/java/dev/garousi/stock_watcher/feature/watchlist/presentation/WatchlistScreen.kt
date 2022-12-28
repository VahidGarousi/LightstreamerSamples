package dev.garousi.stock_watcher.feature.watchlist.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Watchlist(
    viewModel: WatchlistViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        WatchlistTopAppBar {
            Text(
                text = "Stock List",
                modifier = Modifier.align(CenterVertically),
                style = MaterialTheme.typography.h6
            )
        }
        when (val state = uiState) {
            WatchlistState.Error -> ErrorView()
            WatchlistState.Loading -> LoadingView()
            is WatchlistState.Success -> StockList(state.stocks)
        }
    }

}

@Composable
fun WatchlistTopAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    contentPadding: PaddingValues = AppBarDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = RectangleShape,
        modifier = modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(contentPadding)
                .height(56.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = CenterVertically,
            content = content
        )
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ErrorView() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Error Happened",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}