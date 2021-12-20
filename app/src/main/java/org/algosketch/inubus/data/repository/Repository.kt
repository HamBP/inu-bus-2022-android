package org.algosketch.inubus.data.repository

import org.algosketch.inubus.data.model.Memo

interface Repository {
    suspend fun getMemo(): Memo
    suspend fun writeMemo(content: String)
}