package kh.com.pheaktra.developer.basic.android.feature.userapi

import android.graphics.pdf.models.ListItem
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.isLiveLiteralsEnabled
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.feature.bottomsheet.BottomSheetSelectOption
import kh.com.pheaktra.developer.basic.android.model.BaseUiState
import kh.com.pheaktra.developer.basic.android.model.UserModel
import kh.com.pheaktra.developer.basic.android.model.response.UserApiResponse
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import kh.com.pheaktra.developer.basic.android.util.LoadingUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenUserApi(
    userApiVM: UserApiVM = viewModel(),
    onPressBack: () -> Unit
) {
    val context = LocalContext.current

    val userUiState by userApiVM.userListUiState.collectAsStateWithLifecycle()
    val createUserUiState by userApiVM.createUserState.collectAsStateWithLifecycle()
    val deleteUserState by userApiVM.deleteUserState.collectAsStateWithLifecycle()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var isShowCreateSheet by remember { mutableStateOf(false) }
    var expendIndex by remember { mutableIntStateOf(-1) }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun createUser() {
        userApiVM.createUser(
            name = name,
            email = email
        )
    }

    fun onDelete(id: Int) {
        userApiVM.deleteUser(id)
    }

    fun onToastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun onEdit(user: UserApiResponse) {
        name = user.name
        email = user.email
        isShowCreateSheet = true
    }

    LaunchedEffect(Unit) {
        userApiVM.getUserList()
    }

    LaunchedEffect(userUiState) {
        when (val state = userUiState) {
            is BaseUiState.Loading -> LoadingUtil.showLoading()

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
                onToastMessage(state.message)
            }

            is BaseUiState.ErrorWithException -> {
                LoadingUtil.hideLoading()
                onToastMessage(state.message)
            }

            else -> {
                LoadingUtil.hideLoading()
            }
        }
    }

    LaunchedEffect(deleteUserState) {
        when (val state = deleteUserState) {
            is BaseUiState.Loading -> LoadingUtil.showLoading()

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
                userApiVM.getUserList()
                onToastMessage(state.data.message)
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
                onToastMessage(state.message)
            }

            is BaseUiState.ErrorWithException -> {
                LoadingUtil.hideLoading()
                onToastMessage(state.message)
            }

            else -> {
                LoadingUtil.hideLoading()
            }
        }
    }

    LaunchedEffect(createUserUiState) {
        when (val state = createUserUiState) {
            is BaseUiState.Loading -> LoadingUtil.showLoading()

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
                userApiVM.getUserList()
                isShowCreateSheet = false
                name = ""
                email = ""
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
                onToastMessage(state.message)
                isShowCreateSheet = false
            }

            is BaseUiState.ErrorWithException -> {
                LoadingUtil.hideLoading()
                onToastMessage(state.message)
                isShowCreateSheet = false
            }

            else -> {}
        }
    }

    DisposableEffect(Unit) {

        onDispose {
            userApiVM.onDispose()
        }
    }


    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onPressBack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_menu_24),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "User Api Request"
                    )
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_more_vert),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isShowCreateSheet = true
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = null
                )
            }
        }
    ) { padding ->
        when (val state = userUiState) {
            is BaseUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    items(state.data.size) { index ->
                        if (state.data.isNotEmpty()) {
                            UserItem(
                                item = state.data[index],
                                onClick = { user ->
                                    onEdit(user)
                                }
                            ) {
                                IconButton(
                                    onClick = {
                                        expendIndex = index
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_more_vert),
                                        contentDescription = null
                                    )
                                    if (!isShowCreateSheet) {
                                        DropdownMenu(
                                            expanded = expendIndex == index,
                                            onDismissRequest = {
                                                expendIndex = -1
                                            }
                                        ) {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "Delete",
                                                        color = MaterialTheme.colorScheme.error
                                                    )
                                                },
                                                onClick = {
                                                    onDelete(state.data[index].id)
                                                }
                                            )
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "Edit",
                                                        color = MaterialTheme.colorScheme.primary
                                                    )
                                                },
                                                onClick = {
                                                    onEdit(state.data[index])
                                                }
                                            )
                                        }
                                    }

                                }
                            }
                        }
                        HorizontalDivider()
                    }
                }
            }

            else -> {

            }
        }

        if (isShowCreateSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    isShowCreateSheet = false
                    name = ""
                    email = ""
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { value ->
                            name = value
                            nameError = value.isBlank()
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = {
                            Text("Name")
                        },
                        isError = nameError,
                        supportingText = {
                            if (nameError) {
                                Text("Name is required")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            emailError = !isValidEmail(it)
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = {
                            Text("Email")
                        },
                        isError = emailError,
                        supportingText = {
                            if (emailError) {
                                Text("Invalid email address")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    FilledTonalButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        onClick = {
                            isShowCreateSheet = false
                            createUser()
                        },
                        enabled = !nameError && !emailError
                    ) {
                        Text("Create User")
                    }
                }
            }
        }
    }
}

@Composable
fun UserItem(
    item: UserApiResponse,
    onClick: (item: UserApiResponse) -> Unit,
    trailingIcon: @Composable () -> Unit = {}
) {
    val profileCharacter = item.name.first().uppercase()
    Row(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(
                onClick = {
                    onClick(item)
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(56.dp)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(profileCharacter)
        }
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(item.name)
            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
            Text(item.email)


        }
        Spacer(modifier = Modifier.weight(1f))
        trailingIcon()
    }
}


@Preview(showBackground = false)
@Composable
fun ScreenUserApiPreview() {
    BaseTheme() {
        ScreenUserApi() {}
    }
}
