package org.algosketch.inubus.feature.memo

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.algosketch.inubus.global.base.BaseViewModel
import org.algosketch.inubus.global.usecase.GetMemoUseCase
import org.algosketch.inubus.global.usecase.WriteMemoUseCase
import org.koin.core.component.inject

class MemoViewModel : BaseViewModel() {
    val getMemoUseCase: GetMemoUseCase by inject()
    val writeMemoUseCase: WriteMemoUseCase by inject()

    private val _storedMemo = MutableLiveData<String>()
    val storedMemo = _storedMemo
    var inputMemo = MutableLiveData<String?>()

    fun getMemo() {
        CoroutineScope(Dispatchers.Main).launch {
            val memo = getMemoUseCase.run().content
            storedMemo.postValue(memo)
        }
    }

    fun writeMemo() {
        CoroutineScope(Dispatchers.Main).launch {
            writeMemoUseCase.run(inputMemo.value.toString())
        }
    }
}