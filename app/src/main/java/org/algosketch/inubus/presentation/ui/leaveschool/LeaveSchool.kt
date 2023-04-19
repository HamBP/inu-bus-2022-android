package org.algosketch.inubus.presentation.ui.leaveschool

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.algosketch.inubus.R
import org.algosketch.inubus.common.util.Bus
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.presentation.ui.common.BusStopFilter
import org.algosketch.inubus.presentation.ui.common.Chip
import org.algosketch.inubus.presentation.ui.common.RefreshIndicator
import org.algosketch.inubus.presentation.ui.extension.color
import org.algosketch.inubus.presentation.ui.extension.toRestTimeFormat
import org.algosketch.inubus.presentation.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LeaveSchool(
    viewModel: LeaveSchoolViewModel = hiltViewModel(),
    startBusStop: String,
    toDetail: (String, String) -> Unit
) {
    val busList = viewModel.busList.collectAsState()
    val updatedTime by viewModel.currentTime.collectAsState()
    val filter by viewModel.filter.collectAsState()
    val onFilterItemClicked = { filterItem: String ->
        viewModel.filter.value = filterItem
    }

    var isRefreshing by remember {
        mutableStateOf(false)
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
        })
    val pullRefreshModifier = Modifier.pullRefresh(pullRefreshState)

    LaunchedEffect(isRefreshing, filter) {
        viewModel.updateBusList(startBusStop)
        isRefreshing = false
    }

    RefreshIndicator(state = pullRefreshState, refreshing = isRefreshing)

    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 2.dp)
        ) {
            BusStopFilter(
                filterText = filter,
                options = listOf("전체", "인천대입구", "지식정보단지"),
                onFilterItemClicked = onFilterItemClicked,
            )
            Spacer(modifier = Modifier.weight(1f, true))
            Text(text = "${updatedTime}기준")
        }
        Divider(color = grayDivider)
        LazyColumn(
            modifier = pullRefreshModifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            items(items = busList.value) { busArrivalInfo ->
                Box(
                    modifier = Modifier.padding(
                        start = 20.dp,
                        top = 16.dp,
                        bottom = 20.dp
                    )
                ) {
                    BusInfo(busArrivalInfo = busArrivalInfo, toDetail = toDetail)
                }
                Divider(color = grayDivider)
            }
        }
    }
}

@Composable
private fun BusInfo(
    modifier: Modifier = Modifier,
    busArrivalInfo: BusArrivalInfo,
    toDetail: (String, String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            toDetail(busArrivalInfo.busNumber, busArrivalInfo.where)
        }
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
                        .width(68.dp),
                )
            }
            Row {
                busArrivalInfo.stopStations.forEach { busStop ->
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
                .height(24.dp),
        )
    }
}