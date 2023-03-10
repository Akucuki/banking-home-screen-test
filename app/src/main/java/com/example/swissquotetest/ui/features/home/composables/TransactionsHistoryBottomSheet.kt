package com.example.swissquotetest.ui.features.home.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.swissquotetest.R
import com.example.swissquotetest.data.models.domain.TransactionInfo

@Composable
fun TransactionsHistoryBottomSheet(
    modifier: Modifier = Modifier,
    transactions: List<TransactionInfo>,
    onViewAllClick: () -> Unit,
    onTransactionClick: (Int) -> Unit,
    isExpanded: Boolean
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = WindowInsets.systemBars.asPaddingValues()
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp, start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.card_transactions),
                    color = Color.Black,
                    style = MaterialTheme.typography.body1
                )
                AnimatedVisibility(visible = !isExpanded) {
                    Text(
                        modifier = Modifier.clickable(onClick = onViewAllClick),
                        text = stringResource(R.string.view_all),
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Normal)
                    )
                }
            }
        }
        items(items = transactions, key = { it.id }) { transaction ->
            TransactionItem(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(R.drawable.ic_services),
                transactionInfo = transaction,
                onClick = onTransactionClick
            )
        }
    }
}