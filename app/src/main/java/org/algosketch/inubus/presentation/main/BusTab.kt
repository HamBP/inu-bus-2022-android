package org.algosketch.inubus.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.algosketch.inubus.presentation.navigation.NavDestination
import org.algosketch.inubus.presentation.ui.theme.gray
import org.algosketch.inubus.presentation.ui.theme.primary

@Composable
fun BusTabView(toSchoolScreens: List<NavDestination>, onSelected: (NavDestination) -> Unit, currentScreen: NavDestination) {
    Row(
        Modifier
            .selectableGroup()
            .fillMaxWidth()
    ) {
        toSchoolScreens.forEach { screen ->
            val selected = currentScreen == screen

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(52.dp)
                    .weight(1f, true)
                    .selectable(
                        selected = selected,
                        onClick = { onSelected(screen) },
                    )
            ) {
                Column {
                    Spacer(modifier = Modifier.weight(1f, true))
                    Text(
                        text = screen.route,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.padding(bottom = 6.dp))
                    Box(
                        modifier = Modifier
                            .background(color = if (selected) primary else gray)
                            .fillMaxWidth()
                            .height(4.dp)
                    )
                }
            }
        }
    }
}