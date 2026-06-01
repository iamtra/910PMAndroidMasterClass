package kh.com.pheaktra.developer.basic.android.domain.usecase

import kh.com.pheaktra.developer.basic.android.domain.model.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.domain.model.base.BaseUseCase
import kh.com.pheaktra.developer.basic.android.domain.model.request.UserApiRequest
import kh.com.pheaktra.developer.basic.android.domain.model.response.CreateUserResponse
import kh.com.pheaktra.developer.basic.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<UserApiRequest, Flow<BaseUiState<CreateUserResponse>>>() {
    override suspend fun execute(params: UserApiRequest): Flow<BaseUiState<CreateUserResponse>> {
        return flow {
            try {
                emit(BaseUiState.Loading)
                val response = userRepository.createUser(params)

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