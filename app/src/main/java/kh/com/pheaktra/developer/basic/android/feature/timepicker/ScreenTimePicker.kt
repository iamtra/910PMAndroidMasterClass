package kh.com.pheaktra.developer.basic.android.feature.timepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTimePicker() {
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false
    )
    var pickerTime by remember { mutableStateOf(convertTime(timePickerState)) }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_menu_24),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "Time Picker"
                    )
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_more_vert),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                )
            )
        },
        bottomBar = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    pickerTime = convertTime(timePickerState)
                }
            ) {
                Text("Open Time Picker")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = pickerTime,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            TimePicker(
                state = timePickerState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun convertTime(timePickerState: TimePickerState): String {
    val isAfternoon = if (timePickerState.isAfternoon) "PM" else "AM"
    val value = if (timePickerState.is24hour) "" else isAfternoon
    val hours =
        if (timePickerState.is24hour || timePickerState.hour <= 12) timePickerState.hour
        else timePickerState.hour - 12
    return "${hours}:${timePickerState.minute}:$value"
}

@Preview(showBackground = false)
@Composable
fun ScreenTimePickerPreview() {
    BaseTheme() {
        ScreenTimePicker()
    }
}

/**
 * Homework
 * 1. Create time 2 timepicker
 *      - TimePicker
 *      - TimePickerInput
 * 2. Get current date time by default
 * 3. when user picker get the value to display on the screen
 * 4. Testing custom
 *      - Color
 *      - Size
 *      - Apply date picker dialog
 *      - 24hour
 *      - 12 hour
 *      - Display AM and PM
 *      - Check Afternoon or morning
 */