package kh.com.pheaktra.developer.basic.android.feature.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSlider() {

    var sliderPosition1 by remember { mutableFloatStateOf(0.0f) }
    var sliderPosition2 by remember { mutableFloatStateOf(30.0f) }
    var sliderPosition3 by remember { mutableFloatStateOf(30.0f) }

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
                        text = "Slider Component"
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
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Slider(
                modifier = Modifier
                    .padding(16.dp)
//                    .rotate(-90f)
                    .fillMaxWidth()     // full width
                    .height(140.dp)  ,
                value = sliderPosition1,
                onValueChange = {
                    sliderPosition1 = it
                }
            )

            Text(
                text = sliderPosition1.toString(),
                fontSize = 18.sp
            )

            HorizontalDivider()

//            Slider(
//                modifier = Modifier.padding(16.dp),
//                value = sliderPosition2,
//                onValueChange = {
//                    sliderPosition2 = it
//                },
//                colors = SliderDefaults.colors(
//                    thumbColor = MaterialTheme.colorScheme.primary,
//                    activeTrackColor = MaterialTheme.colorScheme.primaryContainer,
//                    inactiveTrackColor = MaterialTheme.colorScheme.background,
//                ),
//                steps = 4,
//                valueRange = 0f..50f
//            )
//
//            Text(
//                text = sliderPosition2.toString(),
//                fontSize = 18.sp
//            )
//
//            Slider(
//                modifier = Modifier.padding(16.dp),
//                value = sliderPosition3,
//                onValueChange = {
//                    sliderPosition3 = it
//                },
//                valueRange = 0f..50f,
//                steps = 4,
//                thumb = {
//                    Box(
//                        modifier = Modifier
//                            .size(24.dp) // control size
//                            .background(
//                                color = MaterialTheme.colorScheme.primary,
//                                shape = CircleShape // make it circle
//                            )
//                    )
//                },
//                colors = SliderDefaults.colors(
//                    thumbColor = MaterialTheme.colorScheme.primary,
//                    activeTrackColor = MaterialTheme.colorScheme.primaryContainer,
//                    inactiveTrackColor = MaterialTheme.colorScheme.background,
//                ),
//                track = { sliderPositions ->
//                    SliderDefaults.Track(
//                        sliderPositions = sliderPositions,
//                        colors = SliderDefaults.colors(
//                            activeTrackColor = MaterialTheme.colorScheme.primaryContainer,
//                            inactiveTrackColor = MaterialTheme.colorScheme.background,
//                        )
//                    )
//                }
//            )

        }
    }
}



@Preview(showBackground = false)
@Composable
fun ScreenSliderPreview() {
    BaseTheme() {
        ScreenSlider()
    }
}