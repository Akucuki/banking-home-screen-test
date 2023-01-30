package com.example.swissquotetest.ui.features.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.swissquotetest.R
import com.example.swissquotetest.data.models.domain.TransactionInfo

@Composable
fun TransactionsHistorySurface(
    modifier: Modifier = Modifier,
    transactions: List<TransactionInfo>
) {
    Surface(
        modifier = modifier,
        shape = remember { RoundedCornerShape(18.dp) },
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(vertical = 10.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp, start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(R.string.card_transactions), color = Color.Black, style = MaterialTheme.typography.body1)
                Text(
                    modifier = Modifier.clickable { },
                    text = stringResource(R.string.view_all),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Normal)
                )
            }
            transactions.forEachIndexed { index, transaction ->
                TransactionItem(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(R.drawable.ic_services),
                    name = transaction.merchant,
                    amount = transaction.amount,
                    type = stringResource(transaction.type.toStringId()),
                    onClick = {}
                )
                if (index != transactions.lastIndex) Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}