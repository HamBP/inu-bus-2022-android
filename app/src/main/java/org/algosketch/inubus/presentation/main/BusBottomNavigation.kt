package org.algosketch.inubus.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.algosketch.inubus.R
import org.algosketch.inubus.presentation.navigation.NavDestination
import org.algosketch.inubus.presentation.ui.theme.primary

@Composable
fun BusBottomNavigation(destinations: List<NavDestination>, onSelected: (NavDestination) -> Unit, currentScreen: NavDestination) {

    Row(
        Modifier
            .selectableGroup()
            .fillMaxWidth()
            .height(46.dp)
    ) {
        destinations.forEach { destination ->
            val selected = currentScreen == destination

            Box(
                modifier = Modifier
                    .background(color = if(selected) primary else Color.White)
                    .fillMaxHeight()
                    .weight(1f)
                    .selectable(
                        selected = selected,
                        onClick = { onSelected(destination) }
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(id = destination.icon ?: R.drawable.school),
                    contentDescription = destination.route,
                    tint = if(selected) Color.White else primary,
                    modifier = Modifier.width(24.dp)
                )
            }
        }
    }
}