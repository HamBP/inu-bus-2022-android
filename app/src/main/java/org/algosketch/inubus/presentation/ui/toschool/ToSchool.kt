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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.pullRefreshIndicatorTransform
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.collectLatest
import org.algosketch.inubus.R
import org.algosketch.inubus.common.util.Bus
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.presentation.ui.common.BusStopFilter
import org.algosketch.inubus.presentation.ui.common.Chip
import org.algosketch.inubus.presentation.ui.extension.color
import org.algosketch.inubus.presentation.ui.extension.toRestTimeFormat
import org.algosketch.inubus.presentation.ui.theme.colorF9
import org.algosketch.inubus.presentation.ui.theme.gray66
import org.algosketch.inubus.presentation.ui.theme.grayDivider

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ToSchool(
    viewModel: ToSchoolViewModel,
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

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            viewModel.updateBusList(startBusStop)
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
                onFilterItemClicked = onFilterItemClicked,
            )
            Spacer(modifier = Modifier.weight(1f, true))
            Text(text = "${updatedTime}기준")
        }
        Divider(color = grayDivider)
        LazyColumn(
            modifier = pullRefreshModifier.fillMaxHeight()
        ) {
            items(items = busList) { busArrivalInfo ->
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun RefreshIndicator(
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
        )
    }
}