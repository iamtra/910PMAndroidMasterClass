package kh.com.pheaktra.developer.basic.android.feature.internetconnection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.service.connectivity.connectivityState
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import kh.com.pheaktra.developer.basic.android.util.extension.ConnectionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenInternetConnection(
    onBack: () -> Unit = {}
) {
    val connectionState by connectivityState()
    val isConnected = connectionState === ConnectionState.Available

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showSheet by remember { mutableStateOf(!isConnected) }

    LaunchedEffect(isConnected) {
        showSheet = !isConnected
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "Internet Connection"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(isConnected) {
                NetworkStatusItem(title = "Network Status", value = "Connected")
            } else {
                NetworkStatusItem(title = "Network Status", value = "Disconnected")
            }
        }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    // Prevent dismiss while still disconnected, or allow and let it reappear
                    showSheet = false
                },
                sheetState = sheetState,
                dragHandle = { BottomSheetDefaults.DragHandle() }
            ) {
                NoInternetSheetContent(
                    onRetry = {
                        // Optionally trigger a manual re-check or just let the flow re-emit
                        showSheet = !isConnected
                    }
                )
            }
        }
    }
}

@Composable
fun NetworkStatusItem(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title
        )
        Text(
            text = value
        )
    }
}

@Preview
@Composable
fun ScreenInternetConnectionPreview() {
    BaseTheme {
        ScreenInternetConnection()
    }
}