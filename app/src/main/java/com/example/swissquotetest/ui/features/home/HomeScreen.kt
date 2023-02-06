package com.example.swissquotetest.ui.features.home

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.swissquotetest.R
import com.example.swissquotetest.data.models.domain.CardInfo
import com.example.swissquotetest.ui.features.home.composables.CardPage
import com.example.swissquotetest.ui.features.home.composables.LabelledIconButton
import com.example.swissquotetest.ui.features.home.composables.TransactionsHistoryBottomSheet
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import kotlinx.coroutines.flow.receiveAsFlow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToTransactionDetails: (Int) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val events = remember(viewModel.events, lifecycleOwner) {
        viewModel.events.receiveAsFlow().flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val cards by viewModel.cards.collectAsState()
    val transactions by viewModel.transactions.collectAsState()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, viewModel::refreshData)
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is HomeEvent.ShowErrorToast -> {
                    Toast.makeText(
                        context,
                        R.string.error_no_internet_connection,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is HomeEvent.ShowCardLockedToast -> {
                    Toast.makeText(
                        context,
                        R.string.card_is_locked,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is HomeEvent.ShowSettingsToast -> {
                    Toast.makeText(
                        context,
                        R.string.no_settings_to_show,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is HomeEvent.ExpandBottomSheet -> bottomSheetScaffoldState.bottomSheetState.expand()
                is HomeEvent.NavigateToTransactionDetails -> {
                    onNavigateToTransactionDetails(event.transactionId)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .pullRefresh(pullRefreshState),
        contentAlignment = Alignment.TopCenter
    ) {
        AnimatedVisibility(visible = !isRefreshing, enter = fadeIn(), exit = fadeOut()) {
            BottomSheetScaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = bottomSheetScaffoldState,
                sheetContent = {
                    TransactionsHistoryBottomSheet(
                        modifier = Modifier.fillMaxWidth(),
                        transactions = transactions,
                        onViewAllClick = viewModel::onViewAllClick,
                        isExpanded = bottomSheetScaffoldState.bottomSheetState.isExpanded,
                        onTransactionClick = viewModel::onTransactionClick
                    )
                },
                sheetPeekHeight = 300.dp,
                sheetShape = remember { RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp) },
                sheetContentColor = Color.White,
                sheetElevation = 8.dp,
            ) {
                HomeContent(
                    modifier = Modifier
                        .padding(it)
                        .verticalScroll(rememberScrollState()),
                    cards = cards,
                    onLockCardClick = viewModel::onLockCardClick,
                    onSettingsClick = viewModel::onSettingsClick
                )
            }
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            contentColor = MaterialTheme.colors.primary
        )
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    cards: List<CardInfo>,
    onLockCardClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = modifier.padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            HorizontalPager(
                count = cards.size,
                contentPadding = remember {
                    PaddingValues(
                        start = 40.dp,
                        end = 40.dp,
                        top = 20.dp
                    )
                },
            ) { page ->
                CardPage(cards[page])
            }
        }
        Row(
            modifier = Modifier.padding(top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            LabelledIconButton(
                text = stringResource(R.string.lock_card),
                painter = painterResource(R.drawable.ic_padlock),
                onClick = onLockCardClick
            )
            LabelledIconButton(
                text = stringResource(R.string.settings),
                painter = painterResource(R.drawable.ic_settings),
                onClick = onSettingsClick
            )
        }
    }
}
