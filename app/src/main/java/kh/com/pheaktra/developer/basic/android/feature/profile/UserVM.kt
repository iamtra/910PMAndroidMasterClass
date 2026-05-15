package kh.com.pheaktra.developer.basic.android.feature.profile.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.pheaktra.developer.basic.android.model.BaseUiState
import kh.com.pheaktra.developer.basic.android.model.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserVM(
    private val userRepository: UserRepository = UserRepository()
) : ViewModel() {
    private val _userUiState: MutableStateFlow<BaseUiState<UserModel>> = MutableStateFlow(BaseUiState.None)
    val userUiState = _userUiState.asStateFlow()

    /**
     * @param id id user that pass form Ui
     */
    fun getUserDetail(id: Int) {
        viewModelScope.launch {
            _userUiState.emit(BaseUiState.Loading)
            userRepository.getUserDetail(id).collect { response ->
                if (response != null) {
                    _userUiState.emit(BaseUiState.Success(response))
                } else {
                    _userUiState.emit(BaseUiState.Error(message = "User not found"))
                }
            }
        }
    }
}
