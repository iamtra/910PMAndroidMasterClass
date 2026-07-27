package kh.com.pheaktra.developer.basic.android.util

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun SystemBarController(
    useDarkStatusBarIcons: Boolean,
    useDarkNavigationBarIcons: Boolean,
) {
    val view = LocalView.current

    if (!view.isInEditMode) {
        DisposableEffect(
            useDarkStatusBarIcons,
            useDarkNavigationBarIcons,
        ) {
            val window = (view.context as Activity).window
            val controller = WindowCompat.getInsetsController(
                window,
                view,
            )

            controller.isAppearanceLightStatusBars =
                useDarkStatusBarIcons

            controller.isAppearanceLightNavigationBars =
                useDarkNavigationBarIcons

            onDispose { }
        }
    }
}