package kh.com.pheaktra.developer.basic.android.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.core.net.toUri
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.tracing.trace
import kh.com.pheaktra.developer.basic.android.feature.accessphoto.ScreenAccessMultiplePhoto
import kh.com.pheaktra.developer.basic.android.feature.accessphoto.ScreenAccessPhoto
import kh.com.pheaktra.developer.basic.android.feature.androidxcamera.ScreenAndroidXCamera
import kh.com.pheaktra.developer.basic.android.feature.androidxcamera.ScreenPreviewImage
import kh.com.pheaktra.developer.basic.android.feature.badge.badge.ScreenBadge
import kh.com.pheaktra.developer.basic.android.feature.biometric.ScreenBiometric
import kh.com.pheaktra.developer.basic.android.feature.bottomsheet.ScreenBottomSheet
import kh.com.pheaktra.developer.basic.android.feature.broadcastreceiver.ScreenBroadcastReceiverAirPlanMode
import kh.com.pheaktra.developer.basic.android.feature.broadcastreceiver.ScreenBroadcastReceiverBattery
import kh.com.pheaktra.developer.basic.android.feature.camera.ScreenCameraLauncher
import kh.com.pheaktra.developer.basic.android.feature.cards.ScreenCards
import kh.com.pheaktra.developer.basic.android.feature.carousel.ScreenHorizontalMultiBrowseCarousel
import kh.com.pheaktra.developer.basic.android.feature.checkbox.ScreenCheckBox
import kh.com.pheaktra.developer.basic.android.feature.chips.ScreenChips
import kh.com.pheaktra.developer.basic.android.feature.datepicker.ScreenDatePicker
import kh.com.pheaktra.developer.basic.android.feature.dialog.ScreenDialog
import kh.com.pheaktra.developer.basic.android.feature.filledbutton.ScreenFilledButton
import kh.com.pheaktra.developer.basic.android.feature.home.ScreenHome
import kh.com.pheaktra.developer.basic.android.feature.iconbuttons.ScreenIconButtons
import kh.com.pheaktra.developer.basic.android.feature.internetconnection.ScreenInternetConnection
import kh.com.pheaktra.developer.basic.android.feature.loading_progress.ScreenScreenLoadingAndProgress
import kh.com.pheaktra.developer.basic.android.feature.location.ScreenLocation
import kh.com.pheaktra.developer.basic.android.feature.menu.ScreenMenu
import kh.com.pheaktra.developer.basic.android.feature.navigationbar.ScreenNavigationBar
import kh.com.pheaktra.developer.basic.android.feature.navigationdrawer.ScreenNavigationDrawer
import kh.com.pheaktra.developer.basic.android.feature.notifcation.notifcation.ScreenNotificationPermission
import kh.com.pheaktra.developer.basic.android.feature.profile.profile.ScreenProfile
import kh.com.pheaktra.developer.basic.android.feature.progress.ScreenProgress
import kh.com.pheaktra.developer.basic.android.feature.radiobutton.ScreenRadioButton
import kh.com.pheaktra.developer.basic.android.feature.roomdatabase.ScreenCreateTask
import kh.com.pheaktra.developer.basic.android.feature.roomdatabase.ScreenRoomDatabase
import kh.com.pheaktra.developer.basic.android.feature.screeninfo.ScreenInfoScreen
import kh.com.pheaktra.developer.basic.android.feature.segmentedbutton.ScreenMultiChoiceSegmentedButtonRow
import kh.com.pheaktra.developer.basic.android.feature.segmentedbutton.ScreenSingleChoiceSegmentedButton
import kh.com.pheaktra.developer.basic.android.feature.selectmultiplephotos.SelectMultiplePhotos
import kh.com.pheaktra.developer.basic.android.feature.selectmultiplevidoes.SelectMultipleVideos
import kh.com.pheaktra.developer.basic.android.feature.selectphotoandvideos.ScreenSelectPhotoAndVideo
import kh.com.pheaktra.developer.basic.android.feature.selectsinglephoto.SelectSinglePhoto
import kh.com.pheaktra.developer.basic.android.feature.selectsinglevideos.SelectSingleVideo
import kh.com.pheaktra.developer.basic.android.feature.slider.ScreenSlider
import kh.com.pheaktra.developer.basic.android.feature.snackbar.ScreenSnackbar
import kh.com.pheaktra.developer.basic.android.feature.switch.ScreenSwitch
import kh.com.pheaktra.developer.basic.android.feature.tabs.ScreenTabs
import kh.com.pheaktra.developer.basic.android.feature.textfield.ScreenTextField
import kh.com.pheaktra.developer.basic.android.feature.timepicker.ScreenTimePicker
import kh.com.pheaktra.developer.basic.android.feature.toolbar.ScreenToolbar
import kh.com.pheaktra.developer.basic.android.feature.tooltips.ScreenToolTips
import kh.com.pheaktra.developer.basic.android.feature.topappbar.ScreenTopAppBar
import kh.com.pheaktra.developer.basic.android.feature.userapi.ScreenUserApi

enum class NotificationType(val value: String) {
    NOTIFICATION("notification-route"),
}

private const val ANIMATION_DURATION = 300
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation(route: String?) {

    val backStack = remember { mutableStateListOf<Any>(HomeScreen) }

    fun onBack() {
        backStack.removeLastOrNull()
    }

    LaunchedEffect(route) {
        when (route) {
            NotificationType.NOTIFICATION.value -> {
                backStack.add(NotificationPermissionScreen)
            }
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(ANIMATION_DURATION)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth / 3 },
                animationSpec = tween(ANIMATION_DURATION)
            )
        },

        popTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth / 3 },
                animationSpec = tween(ANIMATION_DURATION)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(ANIMATION_DURATION)
            )
        },

        entryProvider = entryProvider {

            entry<UserApiScreen> {
                ScreenUserApi(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<HomeScreen> {
                ScreenHome(
                    onClickItem = { screen ->
                        trace("click_item") {
                            backStack.add(screen)
                        }
                    },
                    onClickProfile = { id ->
                        backStack.add(UserProfile(id))
                    }
                )
            }

            entry<BadgeScreen> {
                ScreenBadge(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<MenuScreen> {
                ScreenMenu(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<TabsScreen> {
                ScreenTabs(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<CardsScreen> {
                ScreenCards(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<ChipsScreen> {
                ScreenChips(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<DialogScreen> {
                ScreenDialog(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<SliderScreen> {
                ScreenSlider(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<SwitchScreen> {
                ScreenSwitch(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<ToolbarScreen> {
                ScreenToolbar(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<CheckBoxScreen> {
                ScreenCheckBox(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<ProgressScreen> {
                ScreenProgress(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<SnackbarScreen> {
                ScreenSnackbar(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<ToolTipsScreen> {
                ScreenToolTips(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<TextFieldScreen> {
                ScreenTextField(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<TopAppBarScreen> {
                ScreenTopAppBar(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<DatePickerScreen> {
                ScreenDatePicker(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<TimePickerScreen> {
                ScreenTimePicker(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<BottomSheetScreen> {
                ScreenBottomSheet(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<IconButtonsScreen> {
                ScreenIconButtons(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<RadioButtonScreen> {
                ScreenRadioButton(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<FilledButtonScreen> {
                ScreenFilledButton(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<NavigationBarScreen> {
                ScreenNavigationBar(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<NavigationDrawerScreen> {
                ScreenNavigationDrawer(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<LoadingAndProgressScreen> {
                ScreenScreenLoadingAndProgress(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<SingleChoiceSegmentedButtonScreen> {
                ScreenSingleChoiceSegmentedButton(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<HorizontalMultiBrowseCarouselScreen> {
                ScreenHorizontalMultiBrowseCarousel(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<MultiChoiceSegmentedButtonRowScreen> {
                ScreenMultiChoiceSegmentedButtonRow(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<NotificationPermissionScreen> {
                ScreenNotificationPermission(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<UserProfile> { route ->
                ScreenProfile(
                    id = route.id,
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<AccessPhotoScreen> {
                ScreenAccessPhoto(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<AccessPhotoMultipleScreen> {
                ScreenAccessMultiplePhoto(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<SelectSinglePhots> {
                SelectSinglePhoto(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<SelectMultiplePhots> {
                SelectMultiplePhotos(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<SelectSingleVideos> {
                SelectSingleVideo(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<SelectMultipleVideos> {
                SelectMultipleVideos(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<SelectPhotoAndVideos> {
                ScreenSelectPhotoAndVideo(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<CameraLauncher> {
                ScreenCameraLauncher(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<RoomDatabaseScreen> {
                ScreenRoomDatabase(
                    onBack = {
                        onBack()
                    },
                    onCreateTask = {
                        backStack.add(CreateTaskScreen(null))
                    },
                    onGoToUpdateTask = { task ->
                        backStack.add(CreateTaskScreen(task))
                    }
                )
            }

            entry<CreateTaskScreen> { route ->
                ScreenCreateTask(
                    taskData = route.task,
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<GetLocation> { route ->
                ScreenLocation(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<AndroidXCamera> { route ->
                ScreenAndroidXCamera(
                    onBack = {
                        onBack()
                    },
                    onPreview = { imageUri ->
                        backStack.add(PreviewImage(imageUri = imageUri.toString()))
                    }
                )
            }

            entry<PreviewImage> { route ->
                ScreenPreviewImage(
                    imageUri = route.imageUri.toUri(),
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<NetworkConnectivity> {
                ScreenInternetConnection(
                    onBack = {
                        onBack()
                    }
                )
            }
            entry<Biometric> {
                ScreenBiometric(
                    onBack = {
                        onBack()
                    }
                )
            }
            entry<InfoScreen> {
                ScreenInfoScreen(
                    onBack = {
                        onBack()
                    }
                )
            }
            entry<BroadcastReceiverAirPlanMode> {
                ScreenBroadcastReceiverAirPlanMode(
                    onBack = {
                        onBack()
                    }
                )
            }
            entry<BroadcastReceiverBattery> {
                ScreenBroadcastReceiverBattery(
                    onBack = {
                        onBack()
                    }
                )
            }
        }
    )
}