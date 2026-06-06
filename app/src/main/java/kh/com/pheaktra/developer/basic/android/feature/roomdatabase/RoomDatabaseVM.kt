package kh.com.pheaktra.developer.basic.android.feature.roomdatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.pheaktra.developer.basic.android.data.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.domain.model.TaskModel
import kh.com.pheaktra.developer.basic.android.domain.usecase.CreateTaskUseCase
import kh.com.pheaktra.developer.basic.android.domain.usecase.DeleteTaskUseCase
import kh.com.pheaktra.developer.basic.android.domain.usecase.GetTaskUseCase
import kh.com.pheaktra.developer.basic.android.domain.usecase.UpdateTaskUseCase
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

    private val _taskListUiState =
        MutableStateFlow<BaseUiState<List<TaskModel>>>(BaseUiState.Loading)
    val taskListUiState = _taskListUiState.asStateFlow()

    fun getTaskList() {
        viewModelScope.launch {
            getTaskUseCase(Unit).collect {
                taskListUiState.value = it
            }
        }
    }
}