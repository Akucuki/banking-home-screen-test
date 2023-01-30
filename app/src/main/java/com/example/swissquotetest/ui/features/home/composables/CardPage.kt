package com.example.swissquotetest.ui.features.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.swissquotetest.R
import com.example.swissquotetest.data.models.domain.CardInfo

@Composable
fun CardPage(cardInfo: CardInfo) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.bg_silver_credit_card),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 30.dp),
            text = cardInfo.fullBalanceValue,
            style = MaterialTheme.typography.h3
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.available_amount),
                style = MaterialTheme.typography.caption
            )
            Icon(
                painter = painterResource(R.drawable.ic_info),
                contentDescription = null,
                tint = Color.Blue
            )
        }
    }
}