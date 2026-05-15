package kh.com.pheaktra.developer.basic.android.feature.userapi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.pheaktra.developer.basic.android.model.BaseUiState
import kh.com.pheaktra.developer.basic.android.model.request.UserApiRequest
import kh.com.pheaktra.developer.basic.android.model.request.UserUpdateRequest
import kh.com.pheaktra.developer.basic.android.model.response.CreateUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.DeleteUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.UserApiResponse
import kh.com.pheaktra.developer.basic.android.model.response.UserUpdateResponse
import kh.com.pheaktra.developer.basic.android.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserApiVM : ViewModel() {
    private val _userListUiState: MutableStateFlow<BaseUiState<List<UserApiResponse>>?> =
        MutableStateFlow(null)
    val userListUiState = _userListUiState.asStateFlow()

    private val _createUserUiState: MutableStateFlow<BaseUiState<CreateUserResponse>?> =
        MutableStateFlow(null)
    val createUserState = _createUserUiState.asStateFlow()

    private val _deleteUserUiState: MutableStateFlow<BaseUiState<DeleteUserResponse>?> =
        MutableStateFlow(null)
    val deleteUserState = _deleteUserUiState.asStateFlow()

    private val _updateUserUiState: MutableStateFlow<BaseUiState<UserUpdateResponse>?> =
        MutableStateFlow(null)
    val updateUserState = _updateUserUiState.asStateFlow()

    /**
     * Get user list from api
     */
    fun getUserList() {
        viewModelScope.launch {
            _userListUiState.value = BaseUiState.Loading
            try {
                val response = RetrofitClient.instance.getUsers()
                if (response.isSuccessful) {
                    _userListUiState.value = BaseUiState.Success(response.body() ?: emptyList())
                } else {
                    _userListUiState.value = BaseUiState.Error(response.message())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _userListUiState.value = BaseUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    /**
     * Create user from api
     */
    fun createUser(name: String, email: String) {
        viewModelScope.launch {
            try {
                _createUserUiState.emit(BaseUiState.Loading)
                val body = UserApiRequest(name, email)
                val response = RetrofitClient.instance.createUser(body)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.data != null) {
                        _createUserUiState.emit(BaseUiState.Success(body))
                    }
                } else {
                    _createUserUiState.emit(BaseUiState.Error(response.message()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _createUserUiState.emit(
                    BaseUiState.ErrorWithException(
                        e.message ?: "Unknown error"
                    )
                )
            }
        }
    }

    /**
     * Delete user from api
     * @param id Int
     */
    fun deleteUser(id: Int) {
        viewModelScope.launch {
            _deleteUserUiState.value = BaseUiState.Loading

            try {
                val response = RetrofitClient.instance.deleteUser(id)
                if (response.isSuccessful) {
                    _deleteUserUiState.value = BaseUiState.Success(response.body()!!)
                } else {
                    _deleteUserUiState.value = BaseUiState.Error(response.message())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _deleteUserUiState.value = BaseUiState.ErrorWithException(e.message ?: "Unknown error")
            }
        }
    }

    /**
     * Update user from api
     * @param id Int
     * @param body UserUpdateRequest
     */
    fun updateUser(id: Int, body: UserUpdateRequest) {
        viewModelScope.launch {
            _updateUserUiState.value = BaseUiState.Loading
            try {
                val response = RetrofitClient.instance.updateUser(id, body)

                if (response.isSuccessful) {
                    _updateUserUiState.value = BaseUiState.Success(response.body()!!)
                } else {
                    _updateUserUiState.value = BaseUiState.Error(response.message())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _updateUserUiState.value = BaseUiState.ErrorWithException(e.message ?: "Unknown error")
            }
        }
    }

    fun onDispose() {
        _userListUiState.value = null
        _createUserUiState.value = null
        _deleteUserUiState.value = null
    }
}

/**
 * Create user sample api using node express just one index file  and store in state
 * - Get
 * - POST
 * - PUT
 * - PATCH
 * - DELETE
 * - GET BY ID
 */