package kh.com.pheaktra.developer.basic.android.feature.roomdatabase

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.common.ValueYN
import kh.com.pheaktra.developer.basic.android.data.base.BaseUiState
import kh.com.pheaktra.developer.basic.android.domain.model.TaskModel
import kh.com.pheaktra.developer.basic.android.domain.model.isCompleted
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import kh.com.pheaktra.developer.basic.android.ui.theme.Red40

/**
 * 1. Can get data from database
 * 2. Can create new task
 * 3. Can update task, but state did not reactive (Reactive state)
 * 4. Delete task : Need to test tmr
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenRoomDatabase(
    roomDatabaseVM: RoomDatabaseVM = viewModel(),
    onBack: () -> Unit,
    onCreateTask: () -> Unit,
    onGoToUpdateTask: (TaskModel) -> Unit
) {
    val scrollState = rememberScrollState()

    val taskListUiState by roomDatabaseVM.taskListUiState.collectAsStateWithLifecycle()

    var isLongPress by remember { mutableStateOf(false) }
    var task by remember { mutableStateOf<TaskModel?>(null) }

    fun onUpdateTask(task: TaskModel) {
        val status = if (task.isCompleted()) ValueYN.NO.value else ValueYN.YES.value
        roomDatabaseVM.updateTask(task.copy(completedYN = status))
    }



    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back_ios),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "Room Database"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    onCreateTask()
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Extended floating action button."
                    )
                },
                text = { Text(text = "Create Task") },
            )
        }
    ) { padding ->

        when (val state = taskListUiState) {
            is BaseUiState.Success -> {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.Top,
                ) {
                    for (item in state.data) {
                        TaskItem(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .padding(horizontal = 16.dp)
                                .combinedClickable(
                                    onClick = {
                                        onGoToUpdateTask(item)
                                    },
                                    onLongClick = {
                                        isLongPress = true
                                        task = item
                                    }
                                ),
                            task = item
                        ) {
                            onUpdateTask(item)
                        }
                    }
                    Spacer(modifier = Modifier.height(56.dp))
                }
            }

            else -> {}
        }

        if (isLongPress) {
            BottomSheetTaskAction(
                onDismissRequest = {
                    isLongPress = false
                },
                list = listOf(
                    KeyValue(TaskAction.EDIT, "Edit"),
                    KeyValue(TaskAction.DELETE, "Delete")
                ),
                onClick = { key ->
                    when(key) {
                        TaskAction.EDIT -> {
                            task?.let { value ->
                                onGoToUpdateTask(value)
                                isLongPress = false
                            }
                        }
                        TaskAction.DELETE -> {
                            task?.let { value ->
                                roomDatabaseVM.deleteTask(value.id)
                                isLongPress = false
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun TaskItem(modifier: Modifier = Modifier, task: TaskModel, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .height(96.dp)
            .border(
                width = 1.dp,
                color = if (task.isCompleted()) Red40 else MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.large
            )
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.large
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(
                text = task.taskName,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                textDecoration = if (task.isCompleted()) TextDecoration.LineThrough else null
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = task.description,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis
            )
        }
        RadioButton(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(24.dp),
            selected = task.isCompleted(),
            onClick = {
                onClick()
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = Red40
            )
        )
    }
}


enum class TaskAction {
    EDIT,
    DELETE
}

data class KeyValue(
    val key: TaskAction,
    val value: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetTaskAction(
    list: List<KeyValue>,
    onDismissRequest: () -> Unit,
    onClick: (key: TaskAction) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            list.forEach { item ->
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.large
                        )
                        .clickable {
                            onClick(item.key)
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f),
                        text = item.value
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetTaskActionPreview() {
    val list = listOf(
        KeyValue(TaskAction.EDIT, "Edit"),
        KeyValue(TaskAction.DELETE, "Delete")
    )
    BaseTheme() {
        BottomSheetTaskAction(
            list = list,
            onDismissRequest = {},
            onClick = {}
        )
    }
}


@Preview(showBackground = false)
@Composable
fun ScreenRoomDatabasePreview() {
    val task = TaskModel(
        taskName = "Task Name",
        description = "Description Description Description",
        completedYN = "N"
    )
    BaseTheme() {
        TaskItem(task = task) {

        }
//        ScreenRoomDatabase(
//            onBack = {},
//            onCreateTask = {}
//        )
    }
}

/**
 * Exercise for tooltips
 *  - Create simple tool tip with text
 *  - Customer color, add image, change shape and ...
 *  - Manual action (dismiss, click outsize of tooltip to close)
 *  - Use it with long press, press and hold to show
 *  - Add view model and practice
 *
 */