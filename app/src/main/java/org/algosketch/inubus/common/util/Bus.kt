package org.algosketch.inubus.common.util

object Bus {
    val busNumbers = mapOf(
        "165000514" to "41",
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

    fun getBusStopsByBusNumber(busNumber: String, toSchool: Boolean = true) : List<String> {
        if(toSchool) {
            return when(busNumber) {
                "8" -> listOf("정문", "자연대", "공과대")
                "98" -> listOf("정문", "자연대", "공과대", "송도캠")
                "16", "99" -> listOf("정문")
                "6", "6-1" -> listOf("자연대", "공과대")
                "41" -> listOf("북문", "송도캠")
                else -> throw Exception("UNKNOWN BUS NUMBER")
            }
        }

        return when(busNumber) {
            "16", "8", "98", "42", "43" -> listOf("인천대입구")
            "41", "6", "6-1" -> listOf("지식정보단지")
            "99" -> listOf("인천대입구", "지식정보단지")
            else -> throw Exception("UNKNOWN BUS NUMBER")
        }
    }

    fun getBusStopName(busStopId: String) : String {
        return when(busStopId) {
            "164000395", "164000396" -> "인천대입구"
            "164000403", "164000380" -> "지식정보단지"
            "164000385" -> "정문"
            "164000377" -> "공과대"
            else -> "Unknown"
        }
    }
}