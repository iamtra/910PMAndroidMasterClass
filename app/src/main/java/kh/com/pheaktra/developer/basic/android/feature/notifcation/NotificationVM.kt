package kh.com.pheaktra.developer.basic.android.feature.notifcation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.pheaktra.developer.basic.android.network.ApiService
import kh.com.pheaktra.developer.basic.android.network.RetrofitClient
import kotlinx.coroutines.launch

class NotificationVM (
//    private val apiService: ApiService
) : ViewModel(){
    private val retrofitClient = RetrofitClient.instance

    fun getUserList() {
        viewModelScope.launch {
            val response = retrofitClient.getUsers()
            println("response: $response")
        }
    }

    init {
        getUserList()
    }
}