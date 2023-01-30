package com.example.swissquotetest.ui.features.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun LabelledIconButton(
    modifier: Modifier = Modifier,
    text: String,
    painter: Painter,
    onClick: () -> Unit,
    shape: Shape = remember { RoundedCornerShape(20.dp) }
) {
    Column(
        modifier = modifier
            .clip(shape)
            .clickable(onClick = onClick)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(44.dp)
                .background(color = Color.White, shape = CircleShape)
                .padding(10.dp),
            painter = painter,
            contentDescription = null
        )
        Text(
            modifier = Modifier,
            text = text,
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.button
        )
    }
}