package kh.com.pheaktra.developer.basic.android

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import kh.com.pheaktra.developer.basic.android.navigation.Navigation
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import kh.com.pheaktra.developer.basic.android.util.LoadingContent
import kh.com.pheaktra.developer.basic.android.util.LoadingUtil
import java.time.LocalDateTime
import java.util.Date

class MainActivity : ComponentActivity() {
    private val currentDateTime = LocalDateTime.now()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("====> MainActivity onCreate $currentDateTime")

        enableEdgeToEdge()
        setContent {
            BaseTheme() {

                // Create file App()
                if (LoadingUtil.isLoading.value) {
                    LoadingContent()
                }
                Navigation()
            }
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        println("====> MainActivity onUserLeaveHint")
    }

    /**
     * Cher will test after lifecyle owner
     */
    override fun onStart() {
        super.onStart()
        println("====> MainActivity onStart")
        // Do something
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        println("====> MainActivity onPause")
    }

    override fun onResume() {
        super.onResume()
        println("====> MainActivity onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("====> MainActivity onDestroy $currentDateTime")
    }
}


/**
 * April 23, 2026
 * I will test multiple activity
 * - Use Intent to navigate activity (activity to activity )
 * - Send data via intent
 * - back to navigation, we need to pass the data also
 *
 */


/**
 * homework
 * 1. Testing activity lifecycle
 * 2. Testing LifeCycleOwner in compose
 */

/**
 * - if you know that company have software development, contact them to get more information
 * - Check Linkin, Workina, check out more
 * -
 */


