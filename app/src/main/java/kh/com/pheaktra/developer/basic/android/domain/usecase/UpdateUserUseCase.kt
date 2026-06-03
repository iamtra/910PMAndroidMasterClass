package kh.com.pheaktra.developer.basic.android.domain.usecase

import kh.com.pheaktra.developer.basic.android.data.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.data.request.UpdateUserRequest
import kh.com.pheaktra.developer.basic.android.data.response.UpdateUserResponse
import kh.com.pheaktra.developer.basic.android.domain.BaseUseCase
import kh.com.pheaktra.developer.basic.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<UpdateUserRequest, Flow<BaseUiState<UpdateUserResponse>>>() {
    override suspend fun execute(params: UpdateUserRequest): Flow<BaseUiState<UpdateUserResponse>> {
        return flow {
            try {
                emit(BaseUiState.Loading)
                val response = userRepository.updateUser(
                    id = params.id,
                    user = params
                )

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