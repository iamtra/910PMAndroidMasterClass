package kh.com.pheaktra.developer.basic.android.feature.roomdatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.pheaktra.developer.basic.android.data.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.di.local.entity.Task
import kh.com.pheaktra.developer.basic.android.domain.model.TaskModel
import kh.com.pheaktra.developer.basic.android.domain.usecase.local.DeleteTaskUseCase
import kh.com.pheaktra.developer.basic.android.domain.usecase.local.CreateTaskUseCase
import kh.com.pheaktra.developer.basic.android.domain.usecase.local.GetTaskUseCase
import kh.com.pheaktra.developer.basic.android.domain.usecase.local.UpdateTaskUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomDatabaseVM @Inject constructor(
    private val getTaskUseCase: GetTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    private var _taskListUiState =
        MutableStateFlow<BaseUiState<List<TaskModel>>>(BaseUiState.Loading)
    val taskListUiState = _taskListUiState.asStateFlow()

    private var _createTaskUiState =
        MutableStateFlow<BaseUiState<Unit>>(BaseUiState.None)
    val createTaskUiState = _createTaskUiState.asStateFlow()

    private var _updateTaskUiState =
        MutableStateFlow<BaseUiState<Unit>>(BaseUiState.None)
    val updateTaskUiState = _updateTaskUiState.asStateFlow()


    fun getTaskList() {
        viewModelScope.launch {
            getTaskUseCase(Unit).collect {
                _taskListUiState.emit(it)
            }
        }
    }

    fun createTask(task: TaskModel) {
        viewModelScope.launch {
            createTaskUseCase.invoke(task).collect {
                _createTaskUiState.emit(it)
            }
        }
    }

    fun updateTask(task: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase.invoke(task).collect {
                _updateTaskUiState.emit(it)
            }
        }
    }
}