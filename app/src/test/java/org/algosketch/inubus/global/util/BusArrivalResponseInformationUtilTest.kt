package org.algosketch.inubus.global.util

import junit.framework.TestCase
import org.algosketch.inubus.data.mapper.BusArrivalMapper
import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.data.model.ItemList
import org.algosketch.inubus.data.model.MsgBody

class BusArrivalResponseInformationUtilTest : TestCase() {
    val dummyData: BusArrivalResponse = BusArrivalResponse(null, null, MsgBody(
        itemList = listOf(ItemList(
            ARRIVALESTIMATETIME = 234,
            BSTOPID = "164000395",
            ROUTEID = "165000012",
            REST_STOP_COUNT = 3,
            LATEST_STOP_NAME = "test",
            DIRCD = 1)
        )
    ))

    fun testTransferBusData() {
        val result = BusArrivalMapper.toBusArrival(dummyData)

        assertEquals(result[0].restTime, 3)
        assertEquals(result[0].busNumber, "8")
    }
}