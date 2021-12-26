package org.algosketch.inubus.data.repository

import org.koin.core.component.KoinComponent

class RemoteRepository : Repository, KoinComponent {
//    private val service: MemoService by inject()
//
//    override suspend fun getMemo(): Memo {
//        val result = service.getMemo()
//        Log.d("요청", "status : ${result.code()}, ${result.body()}")
//
//        if(result.isSuccessful) {
//            return result.body()!!
//        }
//        else {
//            throw Exception("UNKNOWN ERROR")
//        }
//    }
//
//    override suspend fun writeMemo(content: String) {
//        val requestBody = Memo(content)
//        val result = service.writeMemo(requestBody)
//        Log.d("요청", "status : ${result.code()}")
//
//        if(!result.isSuccessful) {
//            throw Exception("UNKNOWN ERROR")
//        }
//    }
}