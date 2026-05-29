package kh.com.pheaktra.developer.basic.android.domain.usecase

import kh.com.pheaktra.developer.basic.android.domain.model.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.domain.model.response.GetListUserResponse
import kh.com.pheaktra.developer.basic.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<BaseUiState<GetListUserResponse>> {
        return flow {
            try {
                emit(BaseUiState.Loading)
                val response = userRepository.getUsers()

                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        emit(BaseUiState.Success(body))
                    }
                } else {
                    BaseUiState.Error(
                        message = response.message()
                    )
                }
            } catch (e: Exception) {
                BaseUiState.Error(
                    message = e.message ?: "Unknown error",
                )
            }
        }
    }
}
