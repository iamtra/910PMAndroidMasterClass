package kh.com.pheaktra.developer.kmp.basic.domain.usecase

import kh.com.pheaktra.developer.basic.android.model.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.model.response.DeleteUserResponse
import kh.com.pheaktra.developer.kmp.basic.domain.BaseUseCase
import kh.com.pheaktra.developer.kmp.basic.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<String, Flow<BaseUiState<DeleteUserResponse>>>() {
    override suspend fun execute(params: String): Flow<BaseUiState<DeleteUserResponse>> {
        return flow {
            try {
                emit(BaseUiState.Loading)
                val response = userRepository.deleteUser(params)

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
                BaseUiState.ErrorWithException(
                    message = e.message ?: "Unknown error"
                )
            }
        }
    }
}