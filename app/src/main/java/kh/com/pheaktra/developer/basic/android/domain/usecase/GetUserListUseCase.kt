package kh.com.pheaktra.developer.basic.android.domain.usecase

import kh.com.pheaktra.developer.basic.android.data.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.data.response.GetListUserResponse
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
                    emit(
                        BaseUiState.Error(
                            message = response.message()
                        )
                    )
                }
            } catch (e: Exception) {
                emit(
                    BaseUiState.Error(
                        message = e.message ?: "Unknown error",
                    )
                )
            }
        }
    }
}
