package kh.com.pheaktra.developer.basic.android.feature.roomdatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.pheaktra.developer.basic.android.data.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.domain.model.TaskModel
import kh.com.pheaktra.developer.basic.android.domain.usecase.local.CreateTaskUseCase
import kh.com.pheaktra.developer.basic.android.domain.usecase.local.DeleteTaskUseCase
import kh.com.pheaktra.developer.basic.android.domain.usecase.local.GetTaskUseCase
import kh.com.pheaktra.developer.basic.android.domain.usecase.local.UpdateTaskUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomDatabaseVM @Inject constructor(
    getTaskUseCase: GetTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {
val taskListUiState: StateFlow<BaseUiState<List<TaskModel>>> =
    getTaskUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BaseUiState.Loading
        )

    fun createTask(task: TaskModel) {
        viewModelScope.launch {
            createTaskUseCase.invoke(task).collect {
            }
        }
    }

    fun updateTask(task: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase.invoke(task).collect {
            }
        }
    }

    fun deleteTask(id: Long) {
        viewModelScope.launch {
            deleteTaskUseCase.invoke(id).collect {
            }
        }
    }
}