package kh.com.pheaktra.developer.kmp.basic.domain.usecase

import javax.inject.Inject
import kh.com.pheaktra.developer.basic.android.model.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.model.request.UpdateUserRequest
import kh.com.pheaktra.developer.basic.android.model.response.UpdateUserResponse
import kh.com.pheaktra.developer.kmp.basic.domain.BaseUseCase
import kh.com.pheaktra.developer.kmp.basic.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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