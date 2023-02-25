package org.algosketch.inubus.presentation.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.algosketch.inubus.R
import org.algosketch.inubus.presentation.ui.theme.grayD9

@Composable
fun BusStopFilter(
    filterText: String,
    onFilterItemClicked: (String) -> Unit,
) {
    val filterExpanded = remember {
        mutableStateOf(false)
    }
    val onFilterExpansionChanged = { expanded: Boolean ->
        filterExpanded.value = expanded
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onFilterExpansionChanged(true)
        }
    ) {
        Text(text = filterText)
        Icon(
            painter = painterResource(id = R.drawable.down),
            contentDescription = "bus stop filter dropdown menu",
            tint = grayD9,
            modifier = Modifier.width(12.dp)
        )
    }
    DropdownMenu(
        expanded = filterExpanded.value,
        onDismissRequest = { onFilterExpansionChanged(false) }) {
        val busStops = listOf("전체", "정문", "자연대", "공과대", "인천대 송도캠")
        busStops.forEach { busStop ->
            DropdownMenuItem(onClick = {
                onFilterItemClicked(busStop)
                onFilterExpansionChanged(false)
            }) {
                Text(busStop)
            }
        }
    }
}