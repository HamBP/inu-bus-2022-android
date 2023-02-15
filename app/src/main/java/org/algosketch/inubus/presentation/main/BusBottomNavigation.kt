package org.algosketch.inubus.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.algosketch.inubus.R
import org.algosketch.inubus.presentation.ui.theme.primary

@Composable
fun BusBottomNavigation() {
    Row(
        Modifier
            .selectableGroup()
            .fillMaxWidth()
            .height(46.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = primary)
                .fillMaxHeight()
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.school),
                contentDescription = "등교",
                tint = Color.White,
                modifier = Modifier.width(24.dp)
            )
        }
        Box(
            Modifier
                .background(color = Color.White)
                .fillMaxHeight()
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "하교",
                tint = primary,
                modifier = Modifier.width(24.dp)
            )
        }
    }
}