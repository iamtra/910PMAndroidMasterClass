package kh.com.pheaktra.developer.kmp.basic.domain.usecase.local

import kh.com.pheaktra.developer.basic.android.model.base.BaseUiState
import kh.com.pheaktra.developer.kmp.basic.domain.model.TaskModel
import kh.com.pheaktra.developer.kmp.basic.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(): Flow<BaseUiState<List<TaskModel>>> {
        return repository.getAllTasks()
            .map<List<TaskModel>, BaseUiState<List<TaskModel>>> {
                BaseUiState.Success(it)
            }
            .onStart {
                emit(BaseUiState.Loading)
            }
            .catch { e ->
                emit(BaseUiState.ErrorWithException(e.message ?: ""))
            }
    }
}