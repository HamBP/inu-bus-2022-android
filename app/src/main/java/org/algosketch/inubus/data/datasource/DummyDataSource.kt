package org.algosketch.inubus.data.datasource

import android.util.Log
import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.data.model.ItemList
import org.algosketch.inubus.data.model.MsgBody

class DummyDataSource : DataSource {
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

    override suspend fun getArrivalBusTime(bstopId: String): BusArrivalResponse {
        Log.w("더미데이터 요청", "배포 버전에 올라가면 안 됩니다.")

        return dummyData[bstopId]!!
    }
}
