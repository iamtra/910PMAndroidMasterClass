package kh.com.pheaktra.developer.basic.android.feature.notifcation.notifcation

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.feature.notifcation.NotificationVM
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun ScreenNotificationPermission(
//    notificationVM: NotificationVM = viewModel(),
    onBack: () -> Unit
) {
    val context = LocalContext.current

    val permissionLuncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            createNotificationChannel(context)
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        val isGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
        if (isGranted) {
            Toast.makeText(context, "Permission Granted ==> $isGranted", Toast.LENGTH_SHORT).show()
            return@LaunchedEffect
        }
        permissionLuncher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }

//    val permissionState = rememberPermissionState(
//        permission = Manifest.permission.POST_NOTIFICATIONS
//    )
//
//    LaunchedEffect(Unit) {
//        if (permissionState.status.isGranted) {
//            // Permission is granted
//            print("Permission is granted")
//        } else {
//            // Permission is not granted, request it
//            permissionState.launchPermissionRequest()
//        }
//    }
//
//    LaunchedEffect(permissionState.permission) {
//        println("=====> Hello ")
//        when {
//            permissionState.status.isGranted -> {
//
//            }
//
//            permissionState.status.shouldShowRationale -> {
//
//            }
//
//            else -> {
//
//            }
//        }
//    }


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                                shape = CircleShape
                            ),
                        onClick = onBack,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back_ios),
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text(text = "Notification Permission")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    showLocalNotification(
                        context = context,
                        title = "My first notification",
                        message = "This is my first notification"
                    )
                }
            ) {
                Text("Show Notification")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) { }
    }
}

fun createNotificationChannel(context: Context) {

    val channel = NotificationChannel(
        "default_channel_id",
        "General Notifications",
        NotificationManager.IMPORTANCE_HIGH
    ).apply {
        description = "Used for general app notifications"
    }

    val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    manager.createNotificationChannel(channel)
}

fun showLocalNotification(
    context: Context,
    title: String,
    message: String
) {
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val notification = NotificationCompat.Builder(context, "default_channel_id")
        .setSmallIcon(R.drawable.ic_notification) // must exist
        .setContentTitle(title)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)
        .build()

    notificationManager.notify(System.currentTimeMillis().toInt(), notification)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun ScreenNotificationPermissionPreview() {
    BaseTheme() {
        ScreenNotificationPermission(
            onBack = {}
        )
    }
}