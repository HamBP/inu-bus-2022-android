package org.algosketch.inubus.common.util

object Bus {
    val busNumbers = mapOf(
        "165000515" to "42",
        "165000516" to "43",
        "165000012" to "8",
        "164000001" to "98",
        "161000008" to "99",
        "161000007" to "58",
        "165000020" to "16",
        "165000008" to "6-1",
        "161000008" to "99"
    )

    fun getRouteIdsByBusStop(busStop: String) : List<String> {
        return when(busStop) {
            "164000396" -> listOf("165000515", "165000516")
            "164000395" -> listOf("165000012", "164000001", "161000008", "165000020")
            "164000403" -> listOf("165000008")
            "164000380" -> listOf("161000008")
            "164000385" -> listOf("161000008", "165000020", "165000012") // 정문 -> 인입
            "164000377" -> listOf("165000012", "165000514", "165000515", "165000516", "165000012", "165000008") // 공대 -> 인입, 지정단
            else -> throw Exception("no bus stop id")
        }
    }

    fun getBusColorByBusNumber(busNumber: String) : String {
        return when(busNumber) {
            "8", "16", "58", "6-1", "98" -> "blue"
            "99" -> "purple"
            "41", "42", "43" -> "green"
            else -> "unknown"
        }
    }

    fun getExit(busStop: String) : Int {
        return when(busStop) {
            "164000396" -> 1
            "164000395" -> 2
            "164000403" -> 3
            "164000380" -> 4
            else -> 0
        }
    }

    fun getBusStopsByBusNumber(busNumber: String) : List<String> {
        return when(busNumber) {
            "8" -> listOf("정문", "자연대", "공과대")
            "98" -> listOf("정문", "자연대", "공과대", "송도캠")
            "16", "99" -> listOf("정문")
            "41", "42", "43", "6", "6-1" -> listOf("자연대", "공과대")
            else -> throw Exception("UNKNOWN BUS NUMBER")
        }
    }

    fun getBusStopName(busStopId: String) : String {
        return when(busStopId) {
            "164000395", "164000396" -> "인천대입구"
            "164000403", "164000380" -> "지식정보단지"
            "164000385" -> "공과대"
            "164000387" -> "정문"
            else -> throw Exception("${busStopId} 알 수 없는 버스 정류장입니다.")
        }
    }
}