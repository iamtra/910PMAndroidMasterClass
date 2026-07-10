package kh.com.pheaktra.developer.basic.android.feature.screeninfo

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import android.provider.Settings
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenInfoScreen(
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    val screenInfo = remember {
        getScreenInfo(context)
    }

    DisposableEffect(Unit) {
        println("=====> Dispose effect enter screen")
        onDispose {
            println("=====> Dispose effect Leave screen")
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
                        text = "Screen Info in android"
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

                }
            ) {
                Text("Get Screen Info")
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
            //
            InfoItem("Screen width pixels", "${screenInfo.widthPx}px")
            InfoItem("Screen height pixels", "${screenInfo.heightPx}px")
            InfoItem("Screen width dp", "${screenInfo.widthDp}dp")
            InfoItem("Screen height dp", "${screenInfo.heightDp}dp")
            InfoItem("Screen density", screenInfo.density.toString())
            InfoItem("Screen resolution", screenInfo.resolution)
            InfoItem("Aspect ratio", screenInfo.aspectRatio)
            InfoItem(
                "Physical screen size",
                "%.2f inches".format(screenInfo.physicalSizeInches)
            )
            InfoItem("Screen brightness", screenInfo.brightness.toString())
            InfoItem(
                "Automatic brightness",
                if (screenInfo.isAutoBrightness) "Enabled" else "Disabled"
            )
            InfoItem("Minimum brightness", screenInfo.minBrightness.toString())
            InfoItem("Maximum brightness", screenInfo.maxBrightness.toString())
        }
    }
}
@Composable
fun InfoItem(
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(top = 8.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

data class ScreenInfo(
    val widthPx: Int,
    val heightPx: Int,
    val widthDp: Int,
    val heightDp: Int,
    val density: Float,
    val resolution: String,
    val aspectRatio: String,
    val physicalSizeInches: Double,
    val brightness: Int,
    val isAutoBrightness: Boolean,
    val minBrightness: Int,
    val maxBrightness: Int
)

fun getScreenInfo(context: Context): ScreenInfo {
    val metrics = context.resources.displayMetrics

    val widthPx = metrics.widthPixels
    val heightPx = metrics.heightPixels

    val widthDp = (widthPx / metrics.density).toInt()
    val heightDp = (heightPx / metrics.density).toInt()

    val widthInches = widthPx / metrics.xdpi
    val heightInches = heightPx / metrics.ydpi
    val physicalSize = kotlin.math.sqrt(
        widthInches * widthInches + heightInches * heightInches
    )

    val brightness = Settings.System.getInt(
        context.contentResolver,
        Settings.System.SCREEN_BRIGHTNESS,
        0
    )

    val brightnessMode = Settings.System.getInt(
        context.contentResolver,
        Settings.System.SCREEN_BRIGHTNESS_MODE,
        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
    )

    val gcd = gcd(widthPx, heightPx)

    return ScreenInfo(
        widthPx = widthPx,
        heightPx = heightPx,
        widthDp = widthDp,
        heightDp = heightDp,
        density = metrics.density,
        resolution = "$widthPx x $heightPx",
        aspectRatio = "${widthPx / gcd}:${heightPx / gcd}",
        physicalSizeInches = physicalSize.toDouble(),
        brightness = brightness,
        isAutoBrightness = brightnessMode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC,
        minBrightness = 0,
        maxBrightness = 255
    )
}

fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a else gcd(b, a % b)
}

@Preview
@Composable
fun ScreenInfoScreenPreview() {
    BaseTheme {
        ScreenInfoScreen()
    }
}