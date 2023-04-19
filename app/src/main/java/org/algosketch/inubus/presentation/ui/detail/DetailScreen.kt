package org.algosketch.inubus.presentation.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.algosketch.inubus.R
import org.algosketch.inubus.common.util.Bus
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.domain.entity.BusStops
import org.algosketch.inubus.presentation.ui.theme.*

@Composable
fun DetailScreen(
    busNumber: String,
    navController: NavController,
    busStop: String,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    viewModel.fetchData(busNumber, busStop)

    val toSchool = when (busStop) {
        "인천대입구", "지식정보단지" -> true
        else -> false
    }
    val busStops = if(toSchool) BusStops.toSchool[busNumber] ?: listOf() else BusStops.toHome[busNumber] ?: listOf()
    val busArrival: BusArrivalInfo by viewModel.busArrivalInfo.collectAsState()

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.close),
                contentDescription = "close",
                modifier = Modifier
                    .size(width = 20.dp, height = 20.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(end = 11.dp)
                    .background(
                        color = Bus
                            .getBusColorByBusNumber(busNumber)
                            .color(),
                        shape = RoundedCornerShape(size = 6.dp)
                    )
                    .height(33.dp)
                    .width(52.dp)
                    .align(Alignment.Center),
            ) {
                Text(
                    text = busNumber,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp,
                )
            }
        }
        Divider(color = grayDivider)
        LazyColumn(modifier = Modifier.background(Color.White)) {
            items(items = busStops) { text ->
                BusStop(text = text, text == busArrival.lastStop)
                Divider(color = grayDivider)
            }
        }
    }
}

@Composable
fun BusStop(text: String, isLastStop: Boolean = false) {
    Box {
        Column(
            modifier = Modifier
                .padding(start = 116.dp)
                .align(Alignment.Center)
        ) {
            Text(text)
        }
        Box(
            modifier = Modifier
                .padding(start = 72.dp)
                .size(width = 8.dp, height = 112.dp)
                .background(color = line)
        )
        Image(
            painter = painterResource(id = R.drawable.direction),
            contentDescription = "direction",
            modifier = Modifier
                .padding(start = 64.dp)
                .size(width = 24.dp, height = 24.dp)
                .align(alignment = Alignment.CenterStart)
        )
        if (isLastStop) {
            Image(
                painter = painterResource(id = R.drawable.runner),
                contentDescription = "runner",
                modifier = Modifier
                    .padding(top = 52.dp, start = 54.5.dp)
                    .size(width = 37.dp, height = 57.dp)
                    .align(alignment = Alignment.CenterStart)
            )
        }
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