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
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.tracing.trace
import kh.com.pheaktra.developer.basic.android.feature.accessphoto.ScreenAccessMultiplePhoto
import kh.com.pheaktra.developer.basic.android.feature.accessphoto.ScreenAccessPhoto
import kh.com.pheaktra.developer.basic.android.feature.badge.badge.ScreenBadge
import kh.com.pheaktra.developer.basic.android.feature.bottomsheet.ScreenBottomSheet
import kh.com.pheaktra.developer.basic.android.feature.camera.ScreenCameraLauncher
import kh.com.pheaktra.developer.basic.android.feature.cards.ScreenCards
import kh.com.pheaktra.developer.basic.android.feature.carousel.ScreenHorizontalMultiBrowseCarousel
import kh.com.pheaktra.developer.basic.android.feature.checkbox.ScreenCheckBox
import kh.com.pheaktra.developer.basic.android.feature.chips.ScreenChips
import kh.com.pheaktra.developer.basic.android.feature.datepicker.ScreenDatePicker
import kh.com.pheaktra.developer.basic.android.feature.dialog.ScreenDialog
import kh.com.pheaktra.developer.basic.android.feature.filledbutton.ScreenFilledButton
import kh.com.pheaktra.developer.basic.android.feature.home.home.ScreenHome
import kh.com.pheaktra.developer.basic.android.feature.iconbuttons.ScreenIconButtons
import kh.com.pheaktra.developer.basic.android.feature.loading_progress.ScreenScreenLoadingAndProgress
import kh.com.pheaktra.developer.basic.android.feature.menu.ScreenMenu
import kh.com.pheaktra.developer.basic.android.feature.navigationbar.ScreenNavigationBar
import kh.com.pheaktra.developer.basic.android.feature.navigationdrawer.ScreenNavigationDrawer
import kh.com.pheaktra.developer.basic.android.feature.notifcation.notifcation.ScreenNotificationPermission
import kh.com.pheaktra.developer.basic.android.feature.profile.profile.ScreenProfile
import kh.com.pheaktra.developer.basic.android.feature.progress.ScreenProgress
import kh.com.pheaktra.developer.basic.android.feature.radiobutton.ScreenRadioButton
import kh.com.pheaktra.developer.basic.android.feature.roomdatabase.ScreenCreateTask
import kh.com.pheaktra.developer.basic.android.feature.roomdatabase.ScreenRoomDatabase
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
                    onPressBack = {
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
                ScreenMenu()
            }

            entry<TabsScreen> {
                ScreenTabs()
            }

            entry<CardsScreen> {
                ScreenCards()
            }

            entry<ChipsScreen> {
                ScreenChips()
            }

            entry<DialogScreen> {
                ScreenDialog()
            }

            entry<SliderScreen> {
                ScreenSlider()
            }

            entry<SwitchScreen> {
                ScreenSwitch()
            }

            entry<ToolbarScreen> {
                ScreenToolbar()
            }

            entry<CheckBoxScreen> {
                ScreenCheckBox()
            }

            entry<ProgressScreen> {
                ScreenProgress()
            }

            entry<SnackbarScreen> {
                ScreenSnackbar()
            }

            entry<ToolTipsScreen> {
                ScreenToolTips()
            }

            entry<TextFieldScreen> {
                ScreenTextField()
            }

            entry<TopAppBarScreen> {
                ScreenTopAppBar()
            }

            entry<DatePickerScreen> {
                ScreenDatePicker()
            }

            entry<TimePickerScreen> {
                ScreenTimePicker()
            }

            entry<BottomSheetScreen> {
                ScreenBottomSheet(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<IconButtonsScreen> {
                ScreenIconButtons()
            }

            entry<RadioButtonScreen> {
                ScreenRadioButton(
                    onBack = {
                        onBack()
                    }
                )
            }

            entry<FilledButtonScreen> {
                ScreenFilledButton()
            }

            entry<NavigationBarScreen> {
                ScreenNavigationBar()
            }

            entry<NavigationDrawerScreen> {
                ScreenNavigationDrawer()
            }

            entry<LoadingAndProgressScreen> {
                ScreenScreenLoadingAndProgress()
            }

            entry<SingleChoiceSegmentedButtonScreen> {
                ScreenSingleChoiceSegmentedButton()
            }

            entry<HorizontalMultiBrowseCarouselScreen> {
                ScreenHorizontalMultiBrowseCarousel()
            }

            entry<MultiChoiceSegmentedButtonRowScreen> {
                ScreenMultiChoiceSegmentedButtonRow()
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
                ScreenAccessPhoto()
            }

            entry<AccessPhotoMultipleScreen> {
                ScreenAccessMultiplePhoto()
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
        }
    )
}