package kh.com.pheaktra.developer.kmp.basic.domain.usecase

import kh.com.pheaktra.developer.basic.android.model.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.model.response.GetListUserResponse
import kh.com.pheaktra.developer.kmp.basic.domain.repository.UserRepository
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
