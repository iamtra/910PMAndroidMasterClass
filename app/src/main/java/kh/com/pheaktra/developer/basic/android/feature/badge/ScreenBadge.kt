package kh.com.pheaktra.developer.basic.android.feature.badge.badge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.model.BaseUiState
import kh.com.pheaktra.developer.basic.android.util.LoadingUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenBadge(
    badgeViewModel: BadgeViewModel = BadgeViewModel(),
    onBack: () -> Unit
) {
    val messageUiState by badgeViewModel.messageUiState.collectAsStateWithLifecycle()

    var isHasNewNotification by remember { mutableStateOf(true) }
    var badgeCount by remember { mutableStateOf(0) }
    var message by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        badgeViewModel.requestData()
    }

    LaunchedEffect(messageUiState) {
        when (val state = messageUiState) {
            is BaseUiState.Loading-> {
                println("===> Loading ")
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                println("===> success ")
                LoadingUtil.hideLoading()
                message = state.data
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
            }

            is BaseUiState.ErrorWithException -> {
                LoadingUtil.hideLoading()
            }

            else -> {}
        }
    }


    Scaffold(
        modifier = Modifier.navigationBarsPadding(), topBar = {
            LargeTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "Badge Component"
                    )
                }, actions = {
                    IconButton(
                        onClick = {
                            isHasNewNotification = false
                        }) {
                        BadgedBox(
                            badge = {
                                if (isHasNewNotification) {
                                    Badge()
                                }
                            }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_notification),
                                contentDescription = null
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            isHasNewNotification = false
                        }) {
                        BadgedBox(
                            badge = {
                                if (badgeCount > 0) {
                                    Badge(

                                    ) {
                                        Text(
                                            text = "$badgeCount"
                                        )
                                    }
                                }
                            }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_notification),
                                contentDescription = null
                            )
                        }

                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    navigationIconContentColor = MaterialTheme.colorScheme.error,
//                    actionIconContentColor = MaterialTheme.colorScheme.error,
//                    titleContentColor = MaterialTheme.colorScheme.error
                )
            )
        },
        bottomBar = {
            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    badgeCount++
                }
            ) {
                Text(
                    text = "Update Badge"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(message)

        }
    }
}

@Preview(showBackground = false)
@Composable
fun ScreenBadgePreview() {
//    ScreenBadge()
}