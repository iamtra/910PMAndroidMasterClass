package kh.com.pheaktra.developer.basic.android.feature.navigationbar

import android.net.wifi.hotspot2.pps.HomeSp
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenNavigationBar(
    onBack: () -> Unit = {}
) {
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
                    icon = R.drawable.ic_menu_24,
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
                        text = "NavigationBar"
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
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
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
        when (selectedIndex) {
            0 -> {
                Home(modifier = Modifier.padding(paddingValues = padding))
            }

            1 -> {
                Notification(modifier = Modifier.padding(paddingValues = padding))
            }

            2 -> {
                Settings(modifier = Modifier.padding(paddingValues = padding))
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ScreenNavigationBarPreview() {
    BaseTheme() {
        ScreenNavigationBar()
    }
}

@Composable
fun Home(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home")
    }
}

@Composable
fun Notification(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .background(
                color = MaterialTheme.colorScheme.error
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Notification")
    }
}

@Composable
fun Settings(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .background(
                color = MaterialTheme.colorScheme.primary
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Setting")
    }
}
