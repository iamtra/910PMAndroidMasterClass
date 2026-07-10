package kh.com.pheaktra.developer.basic.android.navigation

import android.net.Uri
import kh.com.pheaktra.developer.kmp.basic.domain.model.TaskModel
import kotlinx.serialization.Serializable

/**
 * Define key for your screen
 */

data object HomeScreen
data object BadgeScreen
data object MenuScreen
data object TabsScreen
data object CardsScreen
data object ChipsScreen
data object DialogScreen
data object SliderScreen
data object SwitchScreen
data object ToolbarScreen
data object CheckBoxScreen
data object ProgressScreen
data object SnackbarScreen
data object ToolTipsScreen
data object TextFieldScreen
data object TopAppBarScreen
data object DatePickerScreen
data object TimePickerScreen
data object BottomSheetScreen
data object IconButtonsScreen
data object RadioButtonScreen
data object FilledButtonScreen
data object NavigationBarScreen
data object NavigationDrawerScreen
data object LoadingAndProgressScreen
data object SingleChoiceSegmentedButtonScreen
data object HorizontalMultiBrowseCarouselScreen
data object MultiChoiceSegmentedButtonRowScreen

data object NotificationPermissionScreen
data object UserApiScreen
data object AccessPhotoScreen
data object AccessPhotoMultipleScreen
data object SelectSinglePhots
data object SelectMultiplePhots
data object SelectSingleVideos
data object SelectMultipleVideos
data object SelectPhotoAndVideos
data object CameraLauncher

data object RoomDatabaseScreen

@Serializable
data class CreateTaskScreen(val task: TaskModel? = null)



@Serializable
data class UserProfile(val id: Int)


data object GetLocation

data object AndroidXCamera

@Serializable
data class PreviewImage(
    val imageUri: String
)

data object NetworkConnectivity

data object Biometric

data object InfoScreen

data object BroadcastReceiverAirPlanMode

data object BroadcastReceiverBattery