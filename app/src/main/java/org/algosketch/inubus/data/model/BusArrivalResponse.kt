package org.algosketch.inubus.data.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "OpenAPI_ServiceResponse")
data class BusArrivalResponse(
    @Element val comMsgHeader: ComMsgHeader?,
    @Element val msgHeader: MsgHeader?,
//    @Element val msgBody: String?
)

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