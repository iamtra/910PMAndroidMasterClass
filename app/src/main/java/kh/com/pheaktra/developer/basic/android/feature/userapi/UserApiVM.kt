package kh.com.pheaktra.developer.basic.android.feature.userapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kh.com.pheaktra.developer.basic.android.model.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.model.request.UserApiRequest
import kh.com.pheaktra.developer.basic.android.model.request.UpdateUserRequest
import kh.com.pheaktra.developer.basic.android.model.response.CreateUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.DeleteUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.GetListUserResponse
import kh.com.pheaktra.developer.basic.android.model.response.UpdateUserResponse
import kh.com.pheaktra.developer.kmp.basic.domain.usecase.CreateUserUseCase
import kh.com.pheaktra.developer.kmp.basic.domain.usecase.DeleteUserUseCase
import kh.com.pheaktra.developer.kmp.basic.domain.usecase.GetUserListUseCase
import kh.com.pheaktra.developer.kmp.basic.domain.usecase.UpdateUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class UserApiVM @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {
    private val _userListUiState: MutableStateFlow<BaseUiState<GetListUserResponse>?> =
        MutableStateFlow(BaseUiState.None)
    val userListUiState = _userListUiState.asStateFlow()

    private val _createUserUiState: MutableStateFlow<BaseUiState<CreateUserResponse>?> =
        MutableStateFlow(null)
    val createUserState = _createUserUiState.asStateFlow()

    private val _deleteUserUiState: MutableStateFlow<BaseUiState<DeleteUserResponse>?> =
        MutableStateFlow(null)
    val deleteUserState = _deleteUserUiState.asStateFlow()

    private val _updateUserUiState: MutableStateFlow<BaseUiState<UpdateUserResponse>?> =
        MutableStateFlow(null)
    val updateUserState = _updateUserUiState.asStateFlow()

    /**
     * Get user list from api
     */
    fun getUserList() {
        viewModelScope.launch {
            getUserListUseCase().collect {
                _userListUiState.emit(it)
            }
        }
    }

    /**
     * Create user from api
     */
    fun createUser(name: String, email: String) {
        val body = UserApiRequest(
            name = name,
            email = email
        )
        viewModelScope.launch {
            createUserUseCase.invoke(params = body).collect {
                _createUserUiState.value = it
            }
        }
    }

    /**
     * Delete user from api
     * @param id Int
     */
    fun deleteUser(id: String) {
        viewModelScope.launch {
            deleteUserUseCase.invoke(params = id)
                .collect(_deleteUserUiState)
        }
    }

    /**
     * Update user from api
     * @param id Int
     * @param body UpdateUserRequest
     */
    fun updateUser(body: UpdateUserRequest) {
        viewModelScope.launch {
            updateUserUseCase.invoke(params = body)
                .collect {
                    _updateUserUiState.emit(it)
                }
        }
    }

    fun onDispose() {
        _userListUiState.value = null
        _createUserUiState.value = null
        _deleteUserUiState.value = null
        _updateUserUiState.value = null
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


/**
 * Hilt dependency injection
 * Request to delay
 * May 19, 2026, move next week 25
 *  - Restructure folder use API.
 */