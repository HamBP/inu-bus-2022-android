package org.algosketch.inubus.presentation.ui.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefreshIndicatorTransform
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RefreshIndicator(
    modifier: Modifier = Modifier,
    state: PullRefreshState,
    refreshing: Boolean
) {
    Box(
        modifier = modifier
            .pullRefreshIndicatorTransform(state, true)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        if (refreshing) {
            val transition = rememberInfiniteTransition()
            val degree by transition.animateFloat(
                initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1000,
                        easing = LinearEasing
                    )
                )
            )
            Icon(
                modifier = Modifier.rotate(degree),
                imageVector = Icons.Rounded.Refresh,
                contentDescription = "refresh"
            )
        } else {
            Icon(
                modifier = Modifier.rotate(state.progress * 180),
                imageVector = Icons.Rounded.Refresh,
                contentDescription = "refresh"
            )
        }
    }
}