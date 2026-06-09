package kh.com.pheaktra.developer.basic.android.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import kh.com.pheaktra.developer.basic.android.feature.accessphoto.ScreenAccessMultiplePhoto
import kh.com.pheaktra.developer.basic.android.feature.accessphoto.ScreenAccessPhoto
import kh.com.pheaktra.developer.basic.android.feature.badge.badge.ScreenBadge
import kh.com.pheaktra.developer.basic.android.feature.bottomsheet.ScreenBottomSheet
import kh.com.pheaktra.developer.basic.android.feature.camera.ScreenCameraLauncher
import kh.com.pheaktra.developer.basic.android.feature.cards.ScreenCards
import kh.com.pheaktra.developer.basic.android.feature.checkbox.ScreenCheckBox
import kh.com.pheaktra.developer.basic.android.feature.chips.ScreenChips
import kh.com.pheaktra.developer.basic.android.feature.datepicker.ScreenDatePicker
import kh.com.pheaktra.developer.basic.android.feature.dialog.ScreenDialog
import kh.com.pheaktra.developer.basic.android.feature.filledbutton.ScreenFilledButton
import kh.com.pheaktra.developer.basic.android.feature.carousel.ScreenHorizontalMultiBrowseCarousel
import kh.com.pheaktra.developer.basic.android.feature.home.home.ScreenHome
import kh.com.pheaktra.developer.basic.android.feature.iconbuttons.ScreenIconButtons
import kh.com.pheaktra.developer.basic.android.feature.menu.ScreenMenu
import kh.com.pheaktra.developer.basic.android.feature.segmentedbutton.ScreenMultiChoiceSegmentedButtonRow
import kh.com.pheaktra.developer.basic.android.feature.navigationbar.ScreenNavigationBar
import kh.com.pheaktra.developer.basic.android.feature.navigationdrawer.ScreenNavigationDrawer
import kh.com.pheaktra.developer.basic.android.feature.progress.ScreenProgress
import kh.com.pheaktra.developer.basic.android.feature.radiobutton.ScreenRadioButton
import kh.com.pheaktra.developer.basic.android.feature.loading_progress.ScreenScreenLoadingAndProgress
import kh.com.pheaktra.developer.basic.android.feature.notifcation.notifcation.ScreenNotificationPermission
import kh.com.pheaktra.developer.basic.android.feature.profile.profile.ScreenProfile
import kh.com.pheaktra.developer.basic.android.feature.roomdatabase.ScreenCreateTask
import kh.com.pheaktra.developer.basic.android.feature.roomdatabase.ScreenRoomDatabase
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
import kh.com.pheaktra.developer.basic.android.feature.tooltips.ScreenToolTips
import kh.com.pheaktra.developer.basic.android.feature.toolbar.ScreenToolbar
import kh.com.pheaktra.developer.basic.android.feature.topappbar.ScreenTopAppBar
import kh.com.pheaktra.developer.basic.android.feature.userapi.ScreenUserApi

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation() {

    val backStack = remember { mutableStateListOf<Any>(HomeScreen) }

    fun onBack() {
        backStack.removeLastOrNull()
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is UserApiScreen -> NavEntry(key) {
                    ScreenUserApi {
                        onBack()
                    }
                }

                is HomeScreen -> NavEntry(key) {
                    ScreenHome(
                        onClickItem = { key ->
                            backStack.add(key)
                        },
                        onClickProfile = { id ->
                            backStack.add(UserProfile(id))
                        }
                    )
                }

                is BadgeScreen -> NavEntry(key) {
                    ScreenBadge(
                        onBack = {

                        }
                    )
                }

                is MenuScreen -> NavEntry(key) { ScreenMenu() }
                is TabsScreen -> NavEntry(key) { ScreenTabs() }
                is CardsScreen -> NavEntry(key) { ScreenCards() }
                is ChipsScreen -> NavEntry(key) { ScreenChips() }
                is DialogScreen -> NavEntry(key) { ScreenDialog() }
                is SliderScreen -> NavEntry(key) { ScreenSlider() }
                is SwitchScreen -> NavEntry(key) { ScreenSwitch() }
                is ToolbarScreen -> NavEntry(key) { ScreenToolbar() }
                is CheckBoxScreen -> NavEntry(key) { ScreenCheckBox() }
                is ProgressScreen -> NavEntry(key) { ScreenProgress() }
                is SnackbarScreen -> NavEntry(key) { ScreenSnackbar() }
                is ToolTipsScreen -> NavEntry(key) { ScreenToolTips() }
                is TextFieldScreen -> NavEntry(key) { ScreenTextField() }
                is TopAppBarScreen -> NavEntry(key) { ScreenTopAppBar() }
                is DatePickerScreen -> NavEntry(key) { ScreenDatePicker() }
                is TimePickerScreen -> NavEntry(key) { ScreenTimePicker() }
                is BottomSheetScreen -> NavEntry(key) { ScreenBottomSheet(onBack = { backStack.removeLastOrNull() }) }
                is IconButtonsScreen -> NavEntry(key) { ScreenIconButtons() }
                is RadioButtonScreen -> NavEntry(key) { ScreenRadioButton(onBack = { backStack.removeLastOrNull() }) }
                is FilledButtonScreen -> NavEntry(key) { ScreenFilledButton() }
                is NavigationBarScreen -> NavEntry(key) { ScreenNavigationBar() }
                is NavigationDrawerScreen -> NavEntry(key) { ScreenNavigationDrawer() }
                is LoadingAndProgressScreen -> NavEntry(key) { ScreenScreenLoadingAndProgress() }
                is SingleChoiceSegmentedButtonScreen -> NavEntry(key) { ScreenSingleChoiceSegmentedButton() }
                is HorizontalMultiBrowseCarouselScreen -> NavEntry(key) { ScreenHorizontalMultiBrowseCarousel() }
                is MultiChoiceSegmentedButtonRowScreen -> NavEntry(key) { ScreenMultiChoiceSegmentedButtonRow() }

                is NotificationPermissionScreen -> NavEntry(key) {
                    ScreenNotificationPermission(
                        onBack = {
                            backStack.removeLastOrNull()
                        }
                    )
                }

                is UserProfile -> NavEntry(key) {
                    ScreenProfile(
                        id = key.id,
                        onBack = {
                            backStack.removeLastOrNull()
                        }
                    )
                }

                is AccessPhotoScreen -> NavEntry(key) {
                    ScreenAccessPhoto()
                }

                is AccessPhotoMultipleScreen -> NavEntry(key) {
                    ScreenAccessMultiplePhoto()
                }

                is SelectSinglePhots -> NavEntry(key) {
                    SelectSinglePhoto {
                        onBack()
                    }
                }

                is SelectMultiplePhots -> NavEntry(key) {
                    SelectMultiplePhotos {
                        onBack()
                    }
                }

                is SelectSingleVideos -> NavEntry(key) {
                    SelectSingleVideo {
                        onBack()
                    }
                }

                is SelectMultipleVideos -> NavEntry(key) {
                    SelectMultipleVideos {
                        onBack()
                    }
                }

                is SelectPhotoAndVideos -> NavEntry(key) {
                    ScreenSelectPhotoAndVideo {
                        onBack()
                    }
                }

                is CameraLauncher -> NavEntry(key) {
                    ScreenCameraLauncher {
                        onBack()
                    }
                }

                is RoomDatabaseScreen -> NavEntry(key) {
                    ScreenRoomDatabase(
                        onBack = {
                            onBack()
                        },
                        onCreateTask = {
                            backStack.add(CreateTaskScreen(null))
                        }
                    )
                }

                is CreateTaskScreen -> NavEntry(key) {
                    ScreenCreateTask(
                        taskData = key.task,
                        onBack = {
                            onBack()
                        },
                        )
                }

                else -> NavEntry(Unit) { Text("Unknown route") }
            }
        }
    )
}
