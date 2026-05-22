package kh.com.pheaktra.developer.basic.android.feature.home.home

import kh.com.pheaktra.developer.basic.android.model.ComponentModel
import kh.com.pheaktra.developer.basic.android.navigation.AccessPhotoMultipleScreen
import kh.com.pheaktra.developer.basic.android.navigation.AccessPhotoScreen
import kh.com.pheaktra.developer.basic.android.navigation.BadgeScreen
import kh.com.pheaktra.developer.basic.android.navigation.BottomSheetScreen
import kh.com.pheaktra.developer.basic.android.navigation.CardsScreen
import kh.com.pheaktra.developer.basic.android.navigation.CheckBoxScreen
import kh.com.pheaktra.developer.basic.android.navigation.ChipsScreen
import kh.com.pheaktra.developer.basic.android.navigation.DatePickerScreen
import kh.com.pheaktra.developer.basic.android.navigation.DialogScreen
import kh.com.pheaktra.developer.basic.android.navigation.FilledButtonScreen
import kh.com.pheaktra.developer.basic.android.navigation.HorizontalMultiBrowseCarouselScreen
import kh.com.pheaktra.developer.basic.android.navigation.IconButtonsScreen
import kh.com.pheaktra.developer.basic.android.navigation.LoadingAndProgressScreen
import kh.com.pheaktra.developer.basic.android.navigation.MenuScreen
import kh.com.pheaktra.developer.basic.android.navigation.MultiChoiceSegmentedButtonRowScreen
import kh.com.pheaktra.developer.basic.android.navigation.NavigationBarScreen
import kh.com.pheaktra.developer.basic.android.navigation.NavigationDrawerScreen
import kh.com.pheaktra.developer.basic.android.navigation.NotificationPermissionScreen
import kh.com.pheaktra.developer.basic.android.navigation.ProgressScreen
import kh.com.pheaktra.developer.basic.android.navigation.RadioButtonScreen
import kh.com.pheaktra.developer.basic.android.navigation.SelectMultiplePhots
import kh.com.pheaktra.developer.basic.android.navigation.SelectMultipleVideos
import kh.com.pheaktra.developer.basic.android.navigation.SelectPhotoAndVideos
import kh.com.pheaktra.developer.basic.android.navigation.SelectSinglePhots
import kh.com.pheaktra.developer.basic.android.navigation.SelectSingleVideos
import kh.com.pheaktra.developer.basic.android.navigation.SingleChoiceSegmentedButtonScreen
import kh.com.pheaktra.developer.basic.android.navigation.SliderScreen
import kh.com.pheaktra.developer.basic.android.navigation.SnackbarScreen
import kh.com.pheaktra.developer.basic.android.navigation.SwitchScreen
import kh.com.pheaktra.developer.basic.android.navigation.TabsScreen
import kh.com.pheaktra.developer.basic.android.navigation.TextFieldScreen
import kh.com.pheaktra.developer.basic.android.navigation.TimePickerScreen
import kh.com.pheaktra.developer.basic.android.navigation.ToolTipsScreen
import kh.com.pheaktra.developer.basic.android.navigation.ToolbarScreen
import kh.com.pheaktra.developer.basic.android.navigation.TopAppBarScreen
import kh.com.pheaktra.developer.basic.android.navigation.UserApiScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepository {
    private val componentList = listOf(
        ComponentModel(
            id = 1,
            title = "Badge",
            description = "Click here to see badge component",
            route = BadgeScreen,
            iconUrl = "https://img.icons8.com/color/96/guarantee.png"
        ),
        ComponentModel(
            id = 2,
            title = "Menu",
            description = "This is Menu component",
            route = MenuScreen,
            iconUrl = "https://img.icons8.com/color/96/menu--v1.png"
        ),
        ComponentModel(
            id = 3,
            title = "Tabs",
            description = "This is Tabs component",
            route = TabsScreen,
            iconUrl = "https://img.icons8.com/color/96/tab.png"
        ),
        ComponentModel(
            id = 4,
            title = "Cards",
            description = "This is Cards component",
            route = CardsScreen,
            iconUrl = "https://img.icons8.com/color/96/bank-cards.png"
        ),
        ComponentModel(
            id = 5,
            title = "Chips",
            description = "This is Chips component",
            route = ChipsScreen,
            iconUrl = "https://img.icons8.com/color/96/chip.png"
        ),
        ComponentModel(
            id = 6,
            title = "Dialog",
            description = "This is Dialog component",
            route = DialogScreen,
            iconUrl = "https://img.icons8.com/color/96/chat--v1.png"
        ),
        ComponentModel(
            id = 7,
            title = "Slider",
            description = "This is Slider component",
            route = SliderScreen,
            iconUrl = "https://img.icons8.com/color/96/slider.png"
        ),
        ComponentModel(
            id = 8,
            title = "Switch",
            description = "This is Switch component",
            route = SwitchScreen,
            iconUrl = "https://img.icons8.com/color/96/switch-on.png"
        ),
        ComponentModel(
            id = 9,
            title = "Toolbar",
            description = "This is Toolbar component",
            route = ToolbarScreen,
            iconUrl = "https://img.icons8.com/color/96/toolbar.png"
        ),
        ComponentModel(
            id = 10,
            title = "CheckBox",
            description = "This is CheckBox component",
            route = CheckBoxScreen,
            iconUrl = "https://img.icons8.com/color/96/checked-checkbox.png"
        ),
        ComponentModel(
            id = 11,
            title = "Progress",
            description = "This is Progress component",
            route = ProgressScreen,
            iconUrl = "https://img.icons8.com/color/96/hourglass.png"
        ),
        ComponentModel(
            id = 12,
            title = "Snackbar",
            description = "This is Snackbar component",
            route = SnackbarScreen,
            iconUrl = "https://img.icons8.com/color/96/info.png"
        ),
        ComponentModel(
            id = 13,
            title = "ToolTips",
            description = "This is ToolTips component",
            route = ToolTipsScreen,
            iconUrl = "https://img.icons8.com/color/96/help.png"
        ),
        ComponentModel(
            id = 14,
            title = "TextField",
            description = "This is TextField component",
            route = TextFieldScreen,
            iconUrl = "https://img.icons8.com/color/96/rename.png"
        ),
        ComponentModel(
            id = 15,
            title = "TopAppBar",
            description = "This is TopAppBar component",
            route = TopAppBarScreen,
            iconUrl = "https://img.icons8.com/color/96/header.png"
        ),
        ComponentModel(
            id = 16,
            title = "DatePicker",
            description = "This is DatePicker component",
            route = DatePickerScreen,
            iconUrl = "https://img.icons8.com/color/96/calendar--v1.png"
        ),
        ComponentModel(
            id = 17,
            title = "TimePicker",
            description = "This is TimePicker component",
            route = TimePickerScreen,
            iconUrl = "https://img.icons8.com/color/96/clock--v1.png"
        ),
        ComponentModel(
            id = 18,
            title = "BottomSheet",
            description = "This is BottomSheet component",
            route = BottomSheetScreen,
            iconUrl = "https://img.icons8.com/color/96/sheet.png"
        ),
        ComponentModel(
            id = 19,
            title = "IconButtons",
            description = "This is IconButtons component",
            route = IconButtonsScreen,
            iconUrl = "https://img.icons8.com/color/96/button.png"
        ),
        ComponentModel(
            id = 20,
            title = "RadioButton",
            description = "This is RadioButton component",
            route = RadioButtonScreen,
            iconUrl = "https://img.icons8.com/color/96/checked-radio-button.png"
        ),
        ComponentModel(
            id = 21,
            title = "FilledButton",
            description = "This is FilledButton component",
            route = FilledButtonScreen,
            iconUrl = "https://img.icons8.com/color/96/ok--v1.png"
        ),
        ComponentModel(
            id = 22,
            title = "NavigationBar",
            description = "This is NavigationBar component",
            route = NavigationBarScreen,
            iconUrl = "https://img.icons8.com/color/96/bottom-navigation.png"
        ),
        ComponentModel(
            id = 23,
            title = "NavigationDrawer",
            description = "This is NavigationDrawer component",
            route = NavigationDrawerScreen,
            iconUrl = "https://img.icons8.com/color/96/sidebar-menu.png"
        ),
        ComponentModel(
            id = 24,
            title = "LoadingAndProgress",
            description = "This is LoadingAndProgress component",
            route = LoadingAndProgressScreen,
            iconUrl = "https://img.icons8.com/color/96/spinner-frame-5.png"
        ),
        ComponentModel(
            id = 25,
            title = "SingleChoiceSegmentedButton",
            description = "This is SingleChoiceSegmentedButton component",
            route = SingleChoiceSegmentedButtonScreen,
            iconUrl = "https://img.icons8.com/color/96/segmented-control.png"
        ),
        ComponentModel(
            id = 26,
            title = "HorizontalMultiBrowseCarousel",
            description = "This is HorizontalMultiBrowseCarousel component",
            route = HorizontalMultiBrowseCarouselScreen,
            iconUrl = "https://img.icons8.com/color/96/carousel.png"
        ),
        ComponentModel(
            id = 27,
            title = "MultiChoiceSegmentedButtonRow",
            description = "This is MultiChoiceSegmentedButtonRow component",
            route = MultiChoiceSegmentedButtonRowScreen,
            iconUrl = "https://img.icons8.com/color/96/list.png"
        ),
        ComponentModel(
            id = 28,
            title = "Notification Permission",
            description = "This is Notification Permission component",
            route = NotificationPermissionScreen,
            iconUrl = "https://img.icons8.com/color/96/bell.png"
        ),
        ComponentModel(
            id = 29,
            title = "User Api Request",
            description = "basic api request feature before becoming a msater",
            route = UserApiScreen,
            iconUrl = "https://img.icons8.com/color/96/bell.png"
        ),
        ComponentModel(
            id = 30,
            title = "Old Access Photo",
            description = "This is Access Photo Feature",
            route = AccessPhotoScreen,
            iconUrl = "https://img.icons8.com/color/96/list.png"
        ),
        ComponentModel(
            id = 31,
            title = "Old Select Multiple Photo",
            description = "This is Access Photo Feature",
            route = AccessPhotoMultipleScreen,
            iconUrl = "https://img.icons8.com/color/96/list.png"
        ),
        ComponentModel(
            id = 32,
            title = "Select Single Photo",
            description = "This is Access Photo Feature",
            route = SelectSinglePhots,
            iconUrl = "https://img.icons8.com/color/96/list.png"
        ),
        ComponentModel(
            id = 33,
            title = "Select Multiple Photos",
            description = "This is Access Photo Feature",
            route = SelectMultiplePhots,
            iconUrl = "https://img.icons8.com/color/96/list.png"
        ),
        ComponentModel(
            id = 34,
            title = "Select Single Video",
            description = "This is Access Photo Feature",
            route = SelectSingleVideos,
            iconUrl = "https://img.icons8.com/color/96/list.png"
        ),
        ComponentModel(
            id = 35,
            title = "Select Multiple Videos",
            description = "This is Access Photo Feature",
            route = SelectMultipleVideos,
            iconUrl = "https://img.icons8.com/color/96/list.png"
        ),

        ComponentModel(
            id = 36,
            title = "Select Photo And Videos",
            description = "This is Access Photo Feature",
            route = SelectPhotoAndVideos,
            iconUrl = "https://img.icons8.com/color/96/list.png"
        ),
    )

    suspend fun getComponentList(): Flow<List<ComponentModel>> {
        delay(500)
        return flow {
            emit(componentList)
        }
    }
}