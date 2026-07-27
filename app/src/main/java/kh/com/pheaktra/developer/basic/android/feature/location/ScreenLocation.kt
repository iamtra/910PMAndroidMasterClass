package kh.com.pheaktra.developer.basic.android.feature.location

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import androidx.core.net.toUri

/**
 * 1. Register permission
 * 2. Request permission
 * 3. Check permission
 * 4. If the permission is already allowed, we can get the location
 * 5. If the permission is not allowed, we can request the permission
 * 6. If the permission is denied, we can show the reason why the permission is denied
 * 7. get the location to display latitude and longitude
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun ScreenLocation(
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current

    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    var latitude by remember { mutableDoubleStateOf(0.0) }
    var longitude by remember { mutableDoubleStateOf(0.0) }

    val locationPermissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
        onPermissionResult = { isGranted ->
            if (isGranted) {
                fusedLocationClient
                    .getCurrentLocation(
                        Priority.PRIORITY_HIGH_ACCURACY,
                        CancellationTokenSource().token
                    )
                    .addOnSuccessListener { location ->
                        if (location != null) {
                            latitude = location.latitude
                            longitude = location.longitude
                            Log.d(
                                "Location",
                                "Latitude: ${location.latitude}, Longitude: ${location.longitude}"
                            )
                        }
                    }
                    .addOnFailureListener {
                        Log.e("Location", it.message.orEmpty())
                    }
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    )


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
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(text = "Location")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            fusedLocationClient
                                .getCurrentLocation(
                                    Priority.PRIORITY_HIGH_ACCURACY,
                                    CancellationTokenSource().token
                                )
                                .addOnSuccessListener { location ->
                                    if (location != null) {
                                        latitude = location.latitude
                                        longitude = location.longitude
                                        Log.d(
                                            "Location",
                                            "Latitude: ${location.latitude}, Longitude: ${location.longitude}"
                                        )
                                    }
                                }
                                .addOnFailureListener {
                                    Log.e("Location", it.message.orEmpty())
                                }
                        } else {
                            locationPermissionState.launchPermissionRequest()
                        }
                    }
                ) {
                    Text("Get Location")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        openGoogleMap(
                            context = context,
                            latitude = latitude,
                            longitude = longitude,
                            label = "Current Location"
                        )
                    }
                ) {
                    Text("Open Google Map")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Latitude: $latitude")
            Text("Longitude: $longitude")
        }
    }
}

fun openGoogleMap(
    context: Context,
    latitude: Double,
    longitude: Double,
    label: String = "Current Location"
) {
    val uri = "geo:$latitude,$longitude?q=$latitude,$longitude($label)".toUri()

    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
        setPackage("com.google.android.apps.maps")
    }

    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        val browserUri =
            "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude".toUri()

        context.startActivity(
            Intent(Intent.ACTION_VIEW, browserUri)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenLocationPreview() {
    BaseTheme {
        ScreenLocation()
    }
}