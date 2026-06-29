package kh.com.pheaktra.developer.basic.android

import android.Manifest
import android.app.ComponentCaller
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import kh.com.pheaktra.developer.basic.android.feature.notifcation.notifcation.createNotificationChannel
import kh.com.pheaktra.developer.basic.android.navigation.Navigation
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import kh.com.pheaktra.developer.basic.android.util.LoadingContent
import kh.com.pheaktra.developer.basic.android.util.LoadingUtil
import kh.com.pheaktra.developer.kmp.basic.core.Engine
import java.time.LocalDateTime

/**
 * Multi modulization in android
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val route = "${intent.getStringExtra("route")}"
        println("=====> intent $${intent.getStringExtra("route")}")
        println("=====> intent $${intent.getStringExtra("id")}")
//        println("====> MainActivity onCreate $currentDateTime")

        enableEdgeToEdge()
        setContent {
            val permissionLuncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) {

                }
            }

            LaunchedEffect(Unit) {
                val isGranted = ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
                if (isGranted) {
                    Toast.makeText(
                        this@MainActivity,
                        "Permission Granted ==> $isGranted",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@LaunchedEffect
                }
                permissionLuncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
            BaseTheme() {

                // Create file App()
                if (LoadingUtil.isLoading.value) {
                    LoadingContent()
                }
                Navigation(route = route)
            }
        }
    }

    override fun onNewIntent(intent: Intent, caller: ComponentCaller) {
        super.onNewIntent(intent, caller)

        println("====> MainActivity onNewIntent")
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
    }

    /**
     * Cher will test after lifecyle owner
     */
    override fun onStart() {
        super.onStart()
        // Do something
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
//        println("====> MainActivity onPause")
    }

    override fun onResume() {
        super.onResume()
//        println("====> MainActivity onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
//        println("====> MainActivity onDestroy $currentDateTime")
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

interface BillPaymentRepository {
    suspend fun getConsumerInfo(id: String): String

    suspend fun getTopUpInfo(id: String): String

    suspend fun getBillPaymentInfo(id: String): String
}
/**
 * Multiple product flavor
 * 1. Dev
 * 2. UAT
 * 3. Production
 */

/**
 * Push notifcation
 * 1. How setup firebase sdk
 *      - Plugin
 *      - Dependency
 *      - Specific any sdk version
 *
 * 2. Firebase account and project
 *      - Account
 *      - Project (Firebase)
 *      - How add application
 *      - download google-service-json
 *      - where to put google-service-json
 *
 * 3. How to test from firebase
 *      - Test send push from firebase
 *      - Test from internal cloud system
 *
 * 4. How to handle user click on notification
 */

/**
 * June 25, 2026
 * - Handle user click on notification
 */

/**
 * June 26, 2026.
 * Restructure navigation to standard navigation
 */

