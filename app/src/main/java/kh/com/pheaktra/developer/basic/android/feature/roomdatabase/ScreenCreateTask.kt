package kh.com.pheaktra.developer.basic.android.feature.roomdatabase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.domain.model.TaskModel
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenCreateTask(
    taskData: TaskModel? = null,
    roomDatabaseVM: RoomDatabaseVM = viewModel(),
    onBack: () -> Unit,
) {

    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var completedYN by remember { mutableStateOf("N") }

    val completedYNList = listOf(
        DropdownItem("Y", "Yes"),
        DropdownItem("N", "No")
    )

    fun onCreate() {
        val task = TaskModel(
            taskName = taskName,
            description = taskDescription,
            completedYN = completedYN
        )
        println("=====> $task")
        roomDatabaseVM.createTask(task)
        onBack()
    }

    fun onUpdateTask() {
        if (taskData != null) {
            val task = taskData.copy(
                taskName = taskName,
                description = taskDescription,
                completedYN = completedYN
            )
            roomDatabaseVM.updateTask(task)
            onBack()
        }
    }

    /**
     * data is not null, it means we are in update mode
     */
    LaunchedEffect(taskData) {
        if (taskData != null) {
            taskName = taskData.taskName
            taskDescription = taskData.description
            completedYN = taskData.completedYN
        }
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
                        text = "Create Task"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                )
            )
        },
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                onClick = {
                    if (taskData == null) {
                        onCreate()
                    } else {
                        onUpdateTask()
                    }
                }
            ) {
                Text(
                    text = if (taskData != null) "Update" else "Create",
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
                value = taskName,
                onValueChange = { value ->
                    if (value.isNotEmpty()) {
                        taskName = value
                    }
                },
                label = {
                    Text(text = "Task Name")
                },
                placeholder = {
                    Text("Enter your task name")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = taskDescription,
                onValueChange = { value ->
                    if (value.isNotEmpty()) {
                        taskDescription = value
                    }
                },
                label = {
                    Text(text = "Description")
                },
                placeholder = {
                    Text("Enter description")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            AppDropdownInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                label = "Completed",
                items = completedYNList,
                selectedItem = completedYNList.find { it.id == completedYN },
                onItemSelected = {
                    completedYN = it.id
                }
            )

        }
    }
}

data class DropdownItem(
    val id: String,
    val title: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropdownInput(
    label: String,
    items: List<DropdownItem>,
    selectedItem: DropdownItem?,
    onItemSelected: (DropdownItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedItem?.title ?: "",
            onValueChange = {},
            readOnly = true,
            label = {
                Text(label)
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(item.title)
                    },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ScreenCreateTaskPreview() {
    BaseTheme() {
        ScreenCreateTask(
            onBack = {},
        )
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