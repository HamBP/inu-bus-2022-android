package org.algosketch.inubus.data.datasource

import android.util.Log
import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.data.model.ItemList
import org.algosketch.inubus.data.model.MsgBody
import org.koin.core.component.KoinComponent

class DummyDataSource : DataSource, KoinComponent {
    val dummyData = listOf(
        BusArrivalResponse(
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
                        ROUTEID = "164000001",
                        REST_STOP_COUNT = 5,
                        LATEST_STOP_NAME = "",
                        DIRCD = 0
                    )
                )
            )
        ),
        BusArrivalResponse(
            msgBody = MsgBody(
                itemList = listOf(
                    ItemList(
                        ARRIVALESTIMATETIME = 350,
                        BSTOPID = "164000403",
                        ROUTEID = "165000008",
                        REST_STOP_COUNT = 5,
                        LATEST_STOP_NAME = "",
                        DIRCD = 0
                    ),
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

        val result = if(bstopId == "164000395" || bstopId == "164000396") dummyData[0] else dummyData[1]

        try {
            return result
        } catch (err: Exception) {
            throw Exception("UNKNOWN ERROR!!")
        }
    }
}
