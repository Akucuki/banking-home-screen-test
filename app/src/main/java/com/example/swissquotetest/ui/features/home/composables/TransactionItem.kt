package com.example.swissquotetest.ui.features.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swissquotetest.R
import com.example.swissquotetest.data.models.domain.TransactionInfo
import com.example.swissquotetest.data.models.domain.TransactionType
import com.example.swissquotetest.ui.theme.SantasGrayColor
import com.example.swissquotetest.ui.theme.StormGrayColor

@Composable
fun TransactionItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    transactionInfo: TransactionInfo,
    onClick: () -> Unit,
) {
    val isGrayedOut = remember { transactionInfo.type == TransactionType.PENDING }
    val textColor = remember { if (isGrayedOut) StormGrayColor else Color.Black }
    val iconColor = remember { if (isGrayedOut) SantasGrayColor else Color.Black }
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(34.dp)
                .background(color = MaterialTheme.colors.background, shape = CircleShape)
                .padding(6.dp),
            painter = painter,
            contentDescription = null,
            tint = iconColor
        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = transactionInfo.merchant,
            color = Color.Black,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = transactionInfo.fullAmountValue,
                color = textColor,
                style = MaterialTheme.typography.body2
            )
            Text(
                text = stringResource(transactionInfo.type.toStringId()),
                color = textColor,
                style = MaterialTheme.typography.caption.copy(fontSize = 12.sp)
            )
        }
        Icon(
            modifier = Modifier.padding(start = 8.dp),
            painter = painterResource(R.drawable.ic_chevron),
            tint = MaterialTheme.colors.secondary,
            contentDescription = null
        )
    }
}