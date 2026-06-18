package kh.com.pheaktra.developer.kmp.basic.domain.usecase.local

import kh.com.pheaktra.developer.basic.android.model.base.BaseUiState
import kh.com.pheaktra.developer.kmp.basic.domain.BaseUseCase
import kh.com.pheaktra.developer.kmp.basic.domain.model.TaskModel
import kh.com.pheaktra.developer.kmp.basic.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) : BaseUseCase<TaskModel, Flow<BaseUiState<Unit>>>() {
    override suspend fun execute(params: TaskModel): Flow<BaseUiState<Unit>> {
        return flow {
            try {
                taskRepository.updateTask(params)
                emit(BaseUiState.Success(Unit))
            } catch (e: IOException) {
                emit(BaseUiState.ErrorWithException(e.message ?: ""))
            }
        }
    }
}