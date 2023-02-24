package org.algosketch.inubus.presentation.ui.inu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import org.algosketch.inubus.R
import org.algosketch.inubus.common.util.Bus
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.presentation.ui.home.ToSchoolViewModel
import org.algosketch.inubus.presentation.ui.theme.*

@Composable
fun InuScreen(viewModel: ToSchoolViewModel, owner: LifecycleOwner, subwayState: String, navController: NavController) {
    val busList = remember { mutableStateOf(viewModel.busList.value) }
    val updatedTime = remember {
        mutableStateOf(viewModel.currentTime.value)
    }
    val filter = remember {
        mutableStateOf(viewModel.filter.value)
    }
    val onFilterItemClicked = { filterItem: String ->
        viewModel.filter.value = filterItem
    }

    viewModel.busList.observe(owner) {
        busList.value = viewModel.busList.value ?: listOf()
    }
    owner.lifecycleScope.launchWhenCreated {
        viewModel.currentTime.collectLatest {
            updatedTime.value = it
        }
    }
    owner.lifecycleScope.launchWhenCreated {
        viewModel.filter.collectLatest {
            filter.value = it
        }
    }

    viewModel.updateBusList(subwayState)

    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 2.dp)
        ) {
            BusStopFilter(
                filterText = filter.value,
                onFilterItemClicked = onFilterItemClicked,
            )
            Spacer(modifier = Modifier.weight(1f, true))
            Text(text = "${updatedTime.value}기준")
        }
        Divider(color = grayDivider)
        LazyColumn {
            items(items = busList.value!!) { busArrivalInfo ->
                Box(
                    modifier = Modifier.padding(
                        start = 20.dp,
                        top = 16.dp,
                        bottom = 20.dp
                    )
                ) {
                    BusInfo(busArrivalInfo = busArrivalInfo, navController = navController)
                }
                Divider(color = grayDivider)
            }
        }
    }
}

@Composable
private fun BusStopFilter(
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

@Composable
private fun BusInfo(modifier: Modifier = Modifier, busArrivalInfo: BusArrivalInfo, navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(end = 11.dp)
                        .background(
                            color = Bus
                                .getBusColorByBusNumber(busArrivalInfo.busNumber)
                                .color(),
                            shape = RoundedCornerShape(size = 6.dp)
                        )
                        .height(33.dp)
                        .width(52.dp),
                ) {
                    Text(
                        text = busArrivalInfo.busNumber,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                    )
                }
                Text(
                    text = busArrivalInfo.restTime.toRestTimeFormat(),
                    color = colorF9,
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .width(64.dp),
                )
                Text(text = "${busArrivalInfo.where} ${busArrivalInfo.exit}번 출구", color = gray66)
            }
            Row {
                Bus.getBusStopsByBusNumber(busArrivalInfo.busNumber).forEach { busStop ->
                    Chip(text = busStop)
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f, true))
        Image(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = "show detail",
            modifier = Modifier
                .padding(end = 16.dp)
                .width(24.dp)
                .height(24.dp)
                .clickable {
                    navController.navigate("DETAIL")
                },
        )
    }
}

private fun Int.toRestTimeFormat(): String {
    if (this < 60) return "곧도착"

    return "${this / 60}분 ${this % 60}초"
}

@Composable
private fun Chip(text: String) {
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

private fun String.color(): Color {
    return when (this) {
        "red" -> busRed
        "blue" -> busBlue
        "purple" -> busPurple
        "green" -> busGreen
        else -> primary
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun WidgetPreview() {
    BusStopFilter("전체", {})
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun BusInfoPreview() {
//    BusInfo(busArrivalInfo = BusArrivalInfo(
//        restTime = 234,
//        busNumber = "8",
//        busColor = "blue",
//        exit = 1,
//        where = "인천대입구",
//        restTimeInformationText = "",
//        exitInformationText = "",
//        navigateDetail = {},
//
//    ))
}
