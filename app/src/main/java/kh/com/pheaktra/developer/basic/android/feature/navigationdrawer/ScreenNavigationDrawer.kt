package kh.com.pheaktra.developer.basic.android.feature.navigationdrawer

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenNavigationDrawer() {
    data class NavigationBarItemModel(
        val id: Int,
        @DrawableRes val icon: Int,
        val label: String,
    )

    var selectedIndex by remember { mutableIntStateOf(0) }
    var navigationItemList by remember {
        mutableStateOf<List<NavigationBarItemModel>>(
            listOf(
                NavigationBarItemModel(
                    id = 1,
                    icon = R.drawable.ic_home,
                    label = "Home",
                ),
                NavigationBarItemModel(
                    id = 2,
                    icon = R.drawable.ic_notification,
                    label = "Notification",
                ),
                NavigationBarItemModel(
                    id = 1,
                    icon = R.drawable.ic_setting,
                    label = "Home",
                )
            )
        )
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf("Hello world !") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RoundedCornerShape(0.dp),
                windowInsets = WindowInsets.navigationBars
            ) {
                DrawerContent { menuItem ->
                    text = menuItem.label
                    scope.launch {
                        selectedIndex = menuItem.id
                        drawerState.close()
                    }
                }
            }
        },
        modifier = Modifier,
        gesturesEnabled = true,
//        scrimColor = TODO()
    ) {

        Scaffold(
//            modifier = Modifier.navigationBarsPadding(),
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (drawerState.isOpen) {
                                        drawerState.close()
                                    } else {
                                        drawerState.open()
                                    }
                                }
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
                            text = "Navigation Drawer"
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    )
                )
            },
            bottomBar = {
                NavigationBar() {
                    navigationItemList.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = index == selectedIndex,
                            icon = {
                                Icon(
                                    painter = painterResource(item.icon),
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(
                                    text = item.label
                                )
                            },
                            onClick = {
                                selectedIndex = index
                            },
                        )
                    }
                }
            }
        ) { padding ->
            when(selectedIndex) {
                0 -> {
                    HomeContent(Modifier.padding(padding))
                }

                1 -> {
                    NotificationContent(Modifier.padding(padding))
                }

                2 -> {
                    SettingContent(Modifier.padding(padding))
                }

                6 -> {
                    Text("Hello Drawer content")
                }

                else -> {
                    Text("Content not found ")
                }
            }
        }
    }
}



data class MenuModel(
    val id: Int,
    val label: String,
    @DrawableRes val leadingIcon: Int,
    @DrawableRes val trailingIcon: Int,
    var selected: Boolean = false,
)

@Composable
fun DrawerContent(onClick: (menuItem: MenuModel) -> Unit) {
    var menuList by remember {
        mutableStateOf<List<MenuModel>>(

            listOf(
                MenuModel(
                    id = 6,
                    label = "Transfer",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification,
                ),
                MenuModel(
                    id = 7,
                    label = "Pay Bills",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 8,
                    label = "Top Up",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 9,
                    label = "Account Details",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 10,
                    label = "Transaction History",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 11,
                    label = "Scan QR",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 12,
                    label = "Request Money",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 13,
                    label = "Cards",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 14,
                    label = "Settings",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                ),
                MenuModel(
                    id = 15,
                    label = "Support",
                    leadingIcon = R.drawable.ic_share,
                    trailingIcon = R.drawable.ic_notification
                )
            )
        )
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.primary)
            .systemBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                ,
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Drawer title",
                color = Color.White,
                fontSize = 18.sp
            )
        }
        HorizontalDivider()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),

        ) {
            menuList.forEachIndexed { index, item ->
                NavigationDrawerItem(
                    label = {
                        Text(item.label)
                    },
                    onClick = {
                        onClick(item)
                    },
                    selected = item.selected,
                    icon = {
                        Icon(
                            painter = painterResource(item.leadingIcon),
                            contentDescription = ""
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedTextColor = MaterialTheme.colorScheme.secondary,
                        unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    )
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun HomeContent(modifier: Modifier) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        items(100) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                text = "This is content home ${it + 1}",
                textAlign = TextAlign.Left
            )
            HorizontalDivider()
        }
    }
}

@Composable
fun NotificationContent(modifier: Modifier) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        items(100) {
            ElevatedCard(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = "This is content home ${it + 1}",
                        textAlign = TextAlign.Left
                    )

                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_more_vert),
                            contentDescription = ""
                        )
                    }
                }
            }
            HorizontalDivider()
        }
    }
}

@Composable
fun SettingContent(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {

//        item {
//            SettingSectionHeader("Network & internet")
//        }

        item {
            SettingItem(
                icon = R.drawable.ic_share,
                title = "Wi-Fi",
                subtitle = "Connected"
            )
        }

        item {
            SettingItem(
                icon = R.drawable.ic_setting,
                title = "Mobile network",
                subtitle = "4G"
            )
        }

        item {
            SettingItem(
                icon = R.drawable.ic_home,
                title = "Hotspot & tethering"
            )
        }

//        item {
//            SettingDivider()
//        }
//
//        item {
//            SettingSectionHeader("Device")
//        }

        item {
            SettingItem(
                icon = R.drawable.ic_notification,
                title = "Display"
            )
        }

        item {
            SettingItem(
                icon = R.drawable.ic_check,
                title = "Sound"
            )
        }

        item {
            SettingItem(
                icon = R.drawable.ic_notification,
                title = "Notifications"
            )
        }
    }
}

@Composable
fun SettingItem(
    icon: Int,
    title: String,
    subtitle: String? = null,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp
                )

                subtitle?.let {
                    Text(
                        text = it,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Icon(
                painter = painterResource(R.drawable.ic_arrow_forward_ios),
                contentDescription = null
            )
        }
        HorizontalDivider()
    }
}

@Preview(showBackground = false)
@Composable
fun ScreenNavigationDrawerPreview() {
    BaseTheme() {
        ScreenNavigationDrawer()
    }
}
