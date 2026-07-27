package kh.com.pheaktra.developer.basic.android.feature.datepicker

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenDatePicker(
    onBack: () -> Unit = {}
) {
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it, DateTimeFormatPattern.EEEE_MMMM_dd_yyyy.format)
    }
    var showDatePicker by remember { mutableStateOf(false) }

    LaunchedEffect(selectedDate) {
        println("==> Select date : $selectedDate")
    }

    LaunchedEffect(Unit) {

    }




    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_menu_24),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "Meal Options"
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
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showDatePicker = true
                }
            ) {
                Text("Open date pciker")
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
                text = "$selectedDate",
                fontSize = 16.sp

            )

            Spacer(modifier = Modifier.height(16.dp))

            if (showDatePicker) {
                Popup(
                    onDismissRequest = {
                        showDatePicker = false
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        DatePicker(
                            modifier = Modifier
                                .padding(16.dp)
                                .clip(shape = RoundedCornerShape(16.dp)),
                            state = datePickerState,
                            title = {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    text = "Select Date of Birth",
                                    textAlign = TextAlign.Center,
                                    fontSize = 24.sp
                                )
                            },
                            showModeToggle = true,
                            colors = DatePickerDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedDayContainerColor = MaterialTheme.colorScheme.error,
                                todayContentColor = MaterialTheme.colorScheme.background
                            )
                        )
                        Row(
                            Modifier.fillMaxWidth()
                        ) {
                            FilledTonalButton(

                                onClick = {
                                    showDatePicker = false
                                }
                            ) {
                                Text("Cancel")
                            }
                            TextButton(
                                onClick = {
                                    showDatePicker = false
                                }
                            ) {
                                Text("Ok ")
                            }
                        }
                    }

                }
            }
        }
    }
}

fun convertMillisToDate(millis: Long, pattern: String): String {
    val formatter = SimpleDateFormat("EEEE, MMMM dd yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

enum class DateTimeFormatPattern(val format: String) {
    DD_MM_YYYY("MM/dd/yyyy"),
    dd_MM_yy("dd/MM/yyyy"),
    yyyy_MM_dd("yyyy-MM-dd"),
    EEE_MMM_dd_yyyy("EEE, MMM dd yyyy"),
    EEEE_MMMM_dd_yyyy("EEEE, MMMM dd yyyy"),
    yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
    hh_mm_a("hh:mm a")

}


@Preview(showBackground = false)
@Composable
fun ScreenDatePickerPreview() {
    BaseTheme() {
        ScreenDatePicker()
    }
}

/**
 * Homework and practice
 *  1. Create all date picker dialog
 *      - Docked date picker
 *      - Modal date picker
 *      - Modal date picker
 *  2. Custom color
 *      - background
 *      - today
 *      - this year
 *      - selected color
 *      - and all
 *  3. Custom title
 *  4. Add Shape
 */