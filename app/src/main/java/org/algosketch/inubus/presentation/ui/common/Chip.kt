package org.algosketch.inubus.presentation.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.algosketch.inubus.presentation.ui.theme.primary

@Composable
fun Chip(text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(end = 16.dp)
            .border(width = 1.dp, color = primary, shape = RoundedCornerShape(size = 6.dp))
            .height(26.dp)
            .padding(horizontal = 12.dp),
    ) {
        Text(
            text = text,
            color = primary,
        )
    }
}