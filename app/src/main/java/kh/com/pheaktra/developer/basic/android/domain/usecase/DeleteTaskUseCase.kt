package kh.com.pheaktra.developer.basic.android.domain.usecase

import kh.com.pheaktra.developer.basic.android.data.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.domain.BaseUseCase
import kh.com.pheaktra.developer.basic.android.domain.model.TaskModel
import kh.com.pheaktra.developer.basic.android.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) : BaseUseCase<TaskModel, Flow<BaseUiState<Unit>>>() {
    override suspend fun execute(params: TaskModel): Flow<BaseUiState<Unit>> {
        return flow {
            try {
                taskRepository.insertTask(params)
                BaseUiState.Success(Unit)
            } catch (e: IOException) {
                emit(BaseUiState.ErrorWithException(e.message ?: ""))
            }
        }
    }
}