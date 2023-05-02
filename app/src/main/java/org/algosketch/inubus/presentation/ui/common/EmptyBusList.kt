package org.algosketch.inubus.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.algosketch.inubus.R

@Composable
fun EmptyBusList(message: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.runner), contentDescription = "알림",
                Modifier
                    .width(71.dp)
                    .height(109.dp)
                    .padding(bottom = 32.dp))
            Text(message)
        }
    }
}