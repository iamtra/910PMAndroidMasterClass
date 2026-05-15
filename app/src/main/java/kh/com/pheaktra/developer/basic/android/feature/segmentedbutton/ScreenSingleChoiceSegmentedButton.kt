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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
fun ScreenSingleChoiceSegmentedButton() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Day", "Month", "Week")

    Scaffold(
        modifier = Modifier.navigationBarsPadding(), topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "Single choice segment button"
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
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                options.forEachIndexed { index, value ->
                    SegmentedButton(
                        modifier = Modifier
                            .weight(1f),
                        selected = if (selectedIndex == index) true else false,
                        onClick = {
                            selectedIndex = index
                        },
                        label = {
                            Text(
                                text = value
                            )
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = SegmentedButtonDefaults.colors(
                            activeBorderColor = MaterialTheme.colorScheme.primaryContainer,
                            inactiveContainerColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }

            when (selectedIndex) {
                0 -> {
                    for (i in 1..100) {
                        Text("Day $i")
                    }
                }

                1 -> {
                    for (i in 1..100) {
                        Text("Months $i")
                    }
                }

                2 -> {
                    for (i in 1..100) {
                        Text("Weeks $i")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ScreenSingleChoiceSegmentedButtonPreview() {
    ScreenSingleChoiceSegmentedButton()
}