package com.example.swissquotetest.ui.features.transactionDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.swissquotetest.R
import com.example.swissquotetest.data.models.domain.TransactionInfo
import com.example.swissquotetest.ui.theme.StormGrayColor

@Composable
fun TransactionDetailsScreen(
    viewModel: TransactionDetailsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {

    val transactionInfo by viewModel.transactionInfo.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isLoadingError by viewModel.isLoadingError.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(80.dp),
                    color = MaterialTheme.colors.primary
                )
            }
            isLoadingError -> {
                Text(
                    text = stringResource(R.string.error_no_information_found_about_transaction),
                    style = MaterialTheme.typography.h3.copy(color = StormGrayColor)
                )
            }
            else -> {
                transactionInfo?.let { info ->
                    TransactionDetailsContent(
                        modifier = Modifier.fillMaxSize(),
                        transactionInfo = info,
                        onBackClick = onNavigateBack
                    )
                }
            }
        }
    }
}

@Composable
fun TransactionDetailsContent(
    modifier: Modifier = Modifier,
    transactionInfo: TransactionInfo,
    onBackClick: () -> Unit
) {
    Column(modifier = modifier) {
        IconButton(onClick = onBackClick) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_back),
                tint = MaterialTheme.colors.primary,
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = remember { RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp) }
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = transactionInfo.merchant,
                style = MaterialTheme.typography.body1
            )
            CategoryTitle(category = transactionInfo.category)
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = transactionInfo.fullAmountValue,
                style = MaterialTheme.typography.h2
            )
            Text(
                text = stringResource(transactionInfo.type.toStringId()),
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
fun CategoryTitle(modifier: Modifier = Modifier, category: String) {
    val roundedCornerShape = remember { RoundedCornerShape(50) }
    Box(modifier = modifier.padding(10.dp), contentAlignment = Alignment.Center) {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp
        )
        Text(
            modifier = Modifier
                .border(
                    width = 8.dp,
                    color = MaterialTheme.colors.surface,
                    shape = roundedCornerShape
                )
                .background(
                    color = MaterialTheme.colors.background,
                    shape = roundedCornerShape
                )
                .padding(18.dp),
            text = category,
            style = MaterialTheme.typography.body2
        )
    }
}