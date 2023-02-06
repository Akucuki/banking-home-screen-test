package com.example.swissquotetest.ui.features.transactionDetails.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


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