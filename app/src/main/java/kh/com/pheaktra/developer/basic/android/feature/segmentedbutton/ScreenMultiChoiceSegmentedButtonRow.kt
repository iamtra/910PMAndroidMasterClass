package kh.com.pheaktra.developer.basic.android.feature.segmentedbutton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMultiChoiceSegmentedButtonRow() {
    val selectedOption = remember { mutableStateListOf(false, false, false) }
    val options = listOf("Day", "Month", "Week")

    Scaffold(
        modifier = Modifier.navigationBarsPadding(), topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "Multi choice segment button"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    navigationIconContentColor = MaterialTheme.colorScheme.error,
//                    actionIconContentColor = MaterialTheme.colorScheme.error,
//                    titleContentColor = MaterialTheme.colorScheme.error
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            MultiChoiceSegmentedButtonRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                options.forEachIndexed { index, value ->
                    SegmentedButton(
                        modifier = Modifier
                            .weight(1f),
                        checked = selectedOption[index],
                        shape = RoundedCornerShape(16.dp),
                        onCheckedChange = { isChecked ->

                            println(selectedOption)
                            println(isChecked)
                            selectedOption[index] = isChecked
                            println(selectedOption)
                        },
                        label = {
                            when(value) {
                                "Day" -> {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_check),
                                        contentDescription = null
                                    )
                                }

                                "Month" -> {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_menu_24),
                                        contentDescription = null
                                    )
                                }

                                "Week" -> {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_notification),
                                        contentDescription = null
                                    )
                                }
                            }
                        },
                        icon = {
                            SegmentedButtonDefaults.Icon(selectedOption[index])
                        }
                    )
                }
            }

        }
    }
}

@Preview(showBackground = false)
@Composable
fun ScreenMultiChoiceSegmentedButtonRowPreview() {
    ScreenMultiChoiceSegmentedButtonRow()
}