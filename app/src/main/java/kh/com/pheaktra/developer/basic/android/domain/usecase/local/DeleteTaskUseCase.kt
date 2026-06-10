package kh.com.pheaktra.developer.basic.android.domain.usecase.local

import kh.com.pheaktra.developer.basic.android.data.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.domain.BaseUseCase
import kh.com.pheaktra.developer.basic.android.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) : BaseUseCase<Long, Flow<BaseUiState<Unit>>>() {
    override suspend fun execute(params: Long): Flow<BaseUiState<Unit>> {
        return flow {
            try {
                taskRepository.deleteTask(taskId = params)
                BaseUiState.Success(Unit)
            } catch (e: IOException) {
                emit(BaseUiState.ErrorWithException(e.message ?: ""))
            }
        }
    }
}