package kh.com.pheaktra.developer.basic.android.feature.menu

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMenu(
    onBack: () -> Unit = {}
) {
    data class MenuModel(
        val id: Int,
        val label: String,
        @DrawableRes val leadingIcon: Int,
        @DrawableRes val trailingIcon: Int,
        var expanded: Boolean = false,
    )

    var expanded by remember { mutableStateOf(false) }
    var menuList by remember {
        mutableStateOf<List<MenuModel>>(

            listOf(
                MenuModel(
                    id = 1,
                    label = "Transfer",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification,
                ),
                MenuModel(
                    id = 2,
                    label = "Pay Bills",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 3,
                    label = "Top Up",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 4,
                    label = "Account Details",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 5,
                    label = "Transaction History",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 6,
                    label = "Scan QR",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 7,
                    label = "Request Money",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 8,
                    label = "Cards",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 9,
                    label = "Settings",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 10,
                    label = "Support",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                )
            )
        )
    }

    fun onUpdateMenu(index: Int) {
        menuList = menuList.map { item ->
            item.copy(expanded = item.id == menuList[index].id)
        }
    }

    fun onResetMenu() {
        menuList = menuList.map { it.copy(expanded = false) }
    }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack()
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
                        text = "Menu Screen"
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            expanded = !expanded
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_more_vert),
                            contentDescription = null
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = !expanded
                            }
                        ) {
                            menuList.forEachIndexed { index, item ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = item.label
                                        )
                                    },
                                    onClick = {
                                        println(item)
                                        expanded = false
                                    },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(item.leadingIcon),
                                            contentDescription = null
                                        )
                                    },
                                    trailingIcon = {
                                        Icon(
                                            painter = painterResource(item.trailingIcon),
                                            contentDescription = null
                                        )
                                    }
                                )
                                if (index != menuList.lastIndex) {
                                    HorizontalDivider()
                                }
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            Row(
                Modifier.fillMaxWidth()
            ) {

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {

                    }
                ) {
                    Text("Show progress")
                }
            }

        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(menuList.size) { index ->

                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(horizontal = 16.dp)
                        .height(64.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary.copy(0.45f)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = menuList[index].label
                    )
                    IconButton(
                        onClick = {
                            onUpdateMenu(index = index)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_more_vert),
                            contentDescription = null
                        )
                        DropdownMenu(
                            expanded = menuList[index].expanded,
                            onDismissRequest = {
                                onResetMenu()
                            }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text("Delete")
                                },
                                onClick = {}
                            )
                            DropdownMenuItem(
                                text = {
                                    Text("Update")
                                },
                                onClick = {}
                            )
                        }
                    }
                }
            }
        }

    }
}

@Preview(showBackground = false)
@Composable
fun ScreenMenuPreview() {
    BaseTheme() {
        ScreenMenu()
    }
}
