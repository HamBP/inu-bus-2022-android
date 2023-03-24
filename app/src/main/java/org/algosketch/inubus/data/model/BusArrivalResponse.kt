package org.algosketch.inubus.data.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import org.algosketch.inubus.common.util.Bus
import org.algosketch.inubus.domain.entity.BusArrivalInfo

@Xml(name = "ServiceResult")
data class BusArrivalResponse(
    @Element val comMsgHeader: ComMsgHeader? = null,
    @Element val msgHeader: MsgHeader? = null,
    @Element val msgBody: MsgBody?
) {
    fun toBusArrivals(): List<BusArrivalInfo> {
        return msgBody!!.itemList!!.map {
            BusArrivalInfo(
                busNumber = Bus.busNumbers[it.ROUTEID] ?: "?",
                busColor = "blue",
                restTime = it.ARRIVALESTIMATETIME,
                lastStop = it.LATEST_STOP_NAME,
            )
        }
    }
}

@Xml(name = "msgHeader")
data class MsgHeader(
    @PropertyElement val pageNo: String?,
    @PropertyElement val resultCode: String?,
    @PropertyElement val totalCount: String?,
    @PropertyElement val numOfRows: String?,
    @PropertyElement val resultMsg: String?)

@Xml(name = "cmmMsgHeader")
data class ComMsgHeader(
    @PropertyElement val errMsg: String?,
    @PropertyElement val returnAuthMsg: String?,
    @PropertyElement val returnReasonCode: String?,
)

@Xml(name = "msgBody")
data class MsgBody(
    @Element val itemList: List<ItemList>?
)

@Xml(name = "itemList")
data class ItemList(
    @PropertyElement val ARRIVALESTIMATETIME: Int,
    @PropertyElement val BSTOPID: String,
    @PropertyElement val ROUTEID: String,
    @PropertyElement val REST_STOP_COUNT: Int,
    @PropertyElement val LATEST_STOP_NAME: String,
    @PropertyElement val DIRCD: Int // 0 상행, 1 하행, 2 순환
)