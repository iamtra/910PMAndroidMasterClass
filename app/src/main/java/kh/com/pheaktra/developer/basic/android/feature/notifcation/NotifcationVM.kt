package kh.com.pheaktra.developer.basic.android.feature.notifcation.notifcation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.pheaktra.developer.basic.android.network.ApiService
import kotlinx.coroutines.launch

class NotifcationVM (
    private val apiService: ApiService
) : ViewModel(){
//    private val retrofitClient = RetrofitClient.instance

    fun getUserList() {
        viewModelScope.launch {
            val response = apiService.getUsers()
            println("response: $response")
        }
    }

    init {
        getUserList()
    }
}