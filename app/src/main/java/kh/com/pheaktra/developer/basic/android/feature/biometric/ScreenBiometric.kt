package kh.com.pheaktra.developer.basic.android.feature.biometric

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.feature.internetconnection.NoInternetSheetContent
import kh.com.pheaktra.developer.basic.android.service.connectivity.connectivityState
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import kh.com.pheaktra.developer.basic.android.util.BiometricUtil
import kh.com.pheaktra.developer.basic.android.util.extension.ConnectionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenBiometric(
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    var message by remember { mutableStateOf("") }


    fun onClickBiometric() {
        when (BiometricUtil.checkBiometricAvailable(context = context)) {
            is BiometricUtil.BiometricStatus.Available -> {
                message = "Available"
                BiometricUtil.showBiometricDialog(
                    activity = context as FragmentActivity,
                    title = "Biometric Authentication",
                    onSuccess = {
                        message = "Success"
                        // Do everything you want
                    },
                    onError = {
                        message = "Error"
                    },
                    onFailed = {
                        message = "Failed"
                    }
                )
            }

            is BiometricUtil.BiometricStatus.NoHardware -> {
                message = "NoHardware"
            }

            is BiometricUtil.BiometricStatus.HardwareUnavailable -> {
                message = "HardwareUnavailable"
            }

            is BiometricUtil.BiometricStatus.NotEnrolled -> {
                message = "NotEnrolled"
            }

            is BiometricUtil.BiometricStatus.SecurityUpdateRequired -> {
                message = "SecurityUpdateRequired"
            }

            is BiometricUtil.BiometricStatus.Unsupported -> {
                message = "Unsupported"
            }

            is BiometricUtil.BiometricStatus.Unknown -> {
                message = "Unknown"
            }
        }
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
                        text = "Biometric"
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
                    .padding(horizontal = 16.dp),
                onClick = {
                    // Call biometric
                    onClickBiometric()
                }
            ) {
                Text("Open Biometric")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(message)
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
        ScreenBiometric()
    }
}