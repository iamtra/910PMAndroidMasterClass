package kh.com.pheaktra.developer.basic.android.feature.camera

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenCameraLauncher(
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    var resultBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            println("=====> Bitmap $bitmap")
            saveBitmapToCache(context, bitmap)
            resultBitmap = bitmap
        } else {
            println("=====>  Not select any image")
        }
    }

    val builder = AlertDialog.Builder(context)

    val permissionLuncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch(null)
        } else {
            builder
                .setIcon(R.drawable.bg_account)
                .setTitle("Permission Denied")
                .setMessage("Please allow permission to access camera")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
            builder.show()
        }
    }

    fun convertBitmap(bitmap: Bitmap): ImageBitmap {
        return bitmap.asImageBitmap()
    }

    fun checkSelfPermission() {
        val isGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
        if (isGranted) {
            cameraLauncher.launch(null)
        } else {
            permissionLuncher.launch(Manifest.permission.CAMERA)
        }
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
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "Camera"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                )
            )
        },
        bottomBar = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    /**
                     * Perform Action
                     */
                    checkSelfPermission()
                }
            ) {
                Text("Open Camera")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                resultBitmap?.let {
                    val imageBitmap = convertBitmap(it)
                    Image(
                        modifier = Modifier
                            .size(
                                width = 1080.dp,
                                height = 1920.dp
                            )
                            .padding(16.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        bitmap = imageBitmap,
                        contentDescription = "Display image Bitmap"
                    )
                }
            }
        }
    }
}

fun saveBitmapToCache(
    context: Context,
    bitmap: Bitmap,
    fileName: String = "image_${System.currentTimeMillis()}.png"
) {
    val file = File(context.cacheDir, fileName)

    FileOutputStream(file).use { outputStream ->
        bitmap.compress(
            Bitmap.CompressFormat.PNG,
            100,
            outputStream
        )
        outputStream.flush()
    }
}

/**
 * Topic Research
 * 1. Android App directories
 * 2. Public directory
 * 3. Private directory
 */

@Preview(showBackground = true)
@Composable
fun ScreenCameraLauncherPreview() {
    BaseTheme() {
        ScreenCameraLauncher()
    }
}
