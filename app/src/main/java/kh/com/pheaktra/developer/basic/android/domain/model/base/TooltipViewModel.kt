package kh.com.pheaktra.developer.basic.android.domain.model.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TooltipViewModel : ViewModel() {
    // Just an example, the actual project won't store the data like this
    private val accountInfo = ReceiverAccountModel(
        accountName = "Pheaktra Developer",
        accountNumber = "19220055",
        receiverBankName = "Chip Mong Commercial Bank"
    )

    private val _receiverAccount = MutableStateFlow<ReceiverAccountModel?>(null)
    val receiverAccount = _receiverAccount.asStateFlow()


    fun getAccountInfo() {
        viewModelScope.launch {
            _receiverAccount.emit(accountInfo)
        }
    }


}