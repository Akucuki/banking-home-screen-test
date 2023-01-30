package com.example.swissquotetest.ui.features.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.swissquotetest.R

@Composable
fun TransactionItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    name: String,
    amount: String,
    type: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(34.dp)
                .background(color = MaterialTheme.colors.background, shape = CircleShape)
                .padding(6.dp),
            painter = painter,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = name,
            color = Color.Black,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = amount,
                color = Color.Black,
                style = MaterialTheme.typography.body2
            )
            Text(
                text = type,
                color = Color.Black,
                style = MaterialTheme.typography.body2
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