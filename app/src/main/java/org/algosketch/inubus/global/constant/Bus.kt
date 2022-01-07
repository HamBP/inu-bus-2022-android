package org.algosketch.inubus.global.constant

object Bus {
    val routeIds = mapOf(
        "42" to "165000515", // 인입 1출 (38396)
        "43" to "165000516",
        "8" to "165000012", // 인입 2출 (38395)
        "98" to "164000001",
        "99" to "161000008",
        "58" to "161000007",
        "16" to "165000020",
        "6" to "???",
        "41" to "???",
        "6-1" to "165000008",
        "99" to "161000008" // 지정단 4출
    )

    val busStopIds = mapOf(
        "42" to "164000396", // 인입 1출 (38396)
        "43" to "164000396",
        "8" to "164000395", // 인입 2출 (38395)
        "98" to "164000395",
        "99" to "164000395",
        "58" to "164000395",
        "16" to "164000395",
        "6" to "???", // 지정단 2출
        "41" to "???",
        "6-1" to "164000403", // 지정단 3출
        "99" to "164000380" // 지정단 4출
    )

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
            "164000395" -> listOf("165000012", "164000001", "161000008", "161000007", "165000020")
            "164000403" -> listOf("165000008")
            "164000380" -> listOf("161000008")
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
}