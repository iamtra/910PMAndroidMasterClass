package kh.com.pheaktra.developer.basic.android.domain.usecase

import kh.com.pheaktra.developer.basic.android.data.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.domain.BaseNoneUseCase
import kh.com.pheaktra.developer.basic.android.domain.BaseUseCase
import kh.com.pheaktra.developer.basic.android.domain.model.TaskModel
import kh.com.pheaktra.developer.basic.android.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) : BaseUseCase<Unit, Flow<BaseUiState<List<TaskModel>>>>() {

    override suspend fun execute(
        params: Unit
    ): Flow<BaseUiState<List<TaskModel>>> {
        return  flow {
            try {
                emit(BaseUiState.Loading)
                val response = repository.getAllTasks()
                emit(BaseUiState.Success(response))
            } catch (e: Exception) {
                emit(BaseUiState.ErrorWithException(e.message ?: ""))
            }
        }
    }
}