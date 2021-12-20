package org.algosketch.inubus.global.usecase

import org.algosketch.inubus.data.model.Memo
import org.algosketch.inubus.data.repository.Repository

class GetMemoUseCase(private val repository: Repository) {
    suspend fun run(): Memo {
        return repository.getMemo()
    }
}