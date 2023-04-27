package org.algosketch.inubus.presentation.ui.toschool

import androidx.compose.animation.core.*
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
import org.algosketch.inubus.presentation.ui.common.*
import org.algosketch.inubus.presentation.ui.extension.color
import org.algosketch.inubus.presentation.ui.extension.toRestTimeFormat
import org.algosketch.inubus.presentation.ui.theme.colorF9
import org.algosketch.inubus.presentation.ui.theme.gray66
import org.algosketch.inubus.presentation.ui.theme.grayDivider

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ToSchool(
    viewModel: ToSchoolViewModel = hiltViewModel(),
    startBusStop: String,
    toDetail: (String, String) -> Unit,
) {
    val busList by viewModel.busList.collectAsState()
    val updatedTime by viewModel.currentTime.collectAsState()
    val filter by viewModel.filter.collectAsState()
    val onFilterItemClicked = { filterItem: String ->
        viewModel.filter.value = filterItem
    }
    var isRefreshing by remember {
        mutableStateOf(false)
    }
    val state by viewModel.state.collectAsState()

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
    when(state) {
        is ToSchoolViewModel.State.Success -> BusList(
            state = state as ToSchoolViewModel.State.Success,
            onFilterItemClicked = onFilterItemClicked,
            pullRefreshModifier = pullRefreshModifier,
            toDetail = toDetail,
        )
        is ToSchoolViewModel.State.Loading -> Box {}
        is ToSchoolViewModel.State.Empty -> EmptyBusList("현재 운행중인 버스 정보가 없습니다")
        else -> EmptyBusList(message = "데이터를 불러올 수 없습니다")
    }
}

@Composable
private fun BusList(state: ToSchoolViewModel.State.Success, onFilterItemClicked: (String) -> Unit, pullRefreshModifier: Modifier, toDetail: (String, String) -> Unit) {

    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 2.dp)
        ) {
            BusStopFilter(
                filterText = state.filter,
                options = listOf("전체", "정문", "자연대", "공과대", "송도캠"),
                onFilterItemClicked = onFilterItemClicked,
            )
            Spacer(modifier = Modifier.weight(1f, true))
            Text(text = "${state.time}기준")
        }
        Divider(color = grayDivider)
        LazyColumn(
            modifier = pullRefreshModifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            items(items = state.list) { busArrivalInfo ->
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
private fun EmptyBusList(message: String) {
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
        )
    }
}