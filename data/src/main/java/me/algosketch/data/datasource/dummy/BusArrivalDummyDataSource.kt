package me.algosketch.data.datasource.dummy

import android.util.Log
import me.algosketch.data.datasource.BusArrivalDataSource
import me.algosketch.data.datasource.model.response.BusArrivalResponse
import me.algosketch.data.datasource.model.response.ItemList
import me.algosketch.data.datasource.model.response.MsgBody

class BusArrivalDummyDataSource : BusArrivalDataSource {
    private val dummyData = hashMapOf(
        "164000396" to BusArrivalResponse(
            msgBody = MsgBody(
                itemList = listOf(
                    ItemList(
                        ARRIVALESTIMATETIME = 350,
                        BSTOPID = "164000396",
                        ROUTEID = "165000515",
                        REST_STOP_COUNT = 5,
                        LATEST_STOP_NAME = "",
                        DIRCD = 0
                    )
                )
            )
        ),
        "164000395" to BusArrivalResponse(
            msgBody = MsgBody(
                itemList = listOf(
                    ItemList(
                        ARRIVALESTIMATETIME = 350,
                        BSTOPID = "164000395",
                        ROUTEID = "165000012",
                        REST_STOP_COUNT = 5,
                        LATEST_STOP_NAME = "",
                        DIRCD = 0
                    ),
                    ItemList(
                        ARRIVALESTIMATETIME = 450,
                        BSTOPID = "164000395",
                        ROUTEID = "161000008",
                        REST_STOP_COUNT = 5,
                        LATEST_STOP_NAME = "",
                        DIRCD = 0
                    )
                )
            )
        ),
        "164000403" to BusArrivalResponse(
            msgBody = MsgBody(
                itemList = listOf(
                    ItemList(
                        ARRIVALESTIMATETIME = 350,
                        BSTOPID = "164000403",
                        ROUTEID = "165000008",
                        REST_STOP_COUNT = 5,
                        LATEST_STOP_NAME = "",
                        DIRCD = 0
                    )
                )
            )
        ),
        "164000380" to BusArrivalResponse(
            msgBody = MsgBody(
                itemList = listOf(
                    ItemList(
                        ARRIVALESTIMATETIME = 450,
                        BSTOPID = "164000380",
                        ROUTEID = "161000008",
                        REST_STOP_COUNT = 5,
                        LATEST_STOP_NAME = "",
                        DIRCD = 0
                    )
                )
            )
        )
    )

    override suspend fun getBusArrival(bstopId: String): BusArrivalResponse {
        Log.w("더미데이터 요청", "배포 버전에 올라가면 안 됩니다.")

        return dummyData[bstopId]!!
    }
}