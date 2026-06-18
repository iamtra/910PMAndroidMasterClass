package kh.com.pheaktra.developer.kmp.basic.domain.usecase.local

import kh.com.pheaktra.developer.basic.android.model.base.BaseUiState
import kh.com.pheaktra.developer.kmp.basic.domain.BaseUseCase
import kh.com.pheaktra.developer.kmp.basic.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) : BaseUseCase<Long, Flow<BaseUiState<Unit>>>() {
    override suspend fun execute(params: Long): Flow<BaseUiState<Unit>> {
        return flow {
            try {
                taskRepository.deleteTask(taskId = params)
                emit(BaseUiState.Success(Unit))
            } catch (e: IOException) {
                emit(BaseUiState.ErrorWithException(e.message ?: ""))
            }
        }
    }
}