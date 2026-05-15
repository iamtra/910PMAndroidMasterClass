package kh.com.pheaktra.developer.basic.android.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    onPrimary = Color.Black,
    primaryContainer = Blue40,
    onPrimaryContainer = Color.Black,

    secondary = PurpleGrey80,
    onSecondary = Color.Black,
    secondaryContainer = PurpleGrey40,
    onSecondaryContainer = Color.White,

    tertiary = Pink80,
    onTertiary = Color.Black,
    tertiaryContainer = Pink40,
    onTertiaryContainer = Color.White,

    background = Color(0xFF121212),
    onBackground = Color.White,

    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,

    error = Red80,
    onError = Color.Black,
    errorContainer = Red40,
    onErrorContainer = Color.White
)

private val LightColorScheme = lightColorScheme(
    // 🎯 Primary
    primary = Purple40,
    onPrimary = Color.White,
    primaryContainer = Blue40,
    onPrimaryContainer = Color.White,

    // 🎯 Secondary
    secondary = PurpleGrey40,
    onSecondary = Color.White,
    secondaryContainer = PurpleGrey80,
    onSecondaryContainer = Color.Black,

    // 🎯 Tertiary
    tertiary = Pink40,
    onTertiary = Color.White,
    tertiaryContainer = Pink80,
    onTertiaryContainer = Color.Black,

    // 🎯 Background & Surface
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),

    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F),

    surfaceVariant = PurpleGrey80,
    onSurfaceVariant = Color(0xFF49454F),

    // 🎯 Error
    error = Red40,
    onError = Color.White,
    errorContainer = Red80,
    onErrorContainer = Color.Black,

    // 🎯 Outline & Misc
    outline = Color(0xFF79747E),
    inverseSurface = Color(0xFF313033),
    inverseOnSurface = Color(0xFFF4EFF4),
    inversePrimary = Purple80
)
@Composable
fun BaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}