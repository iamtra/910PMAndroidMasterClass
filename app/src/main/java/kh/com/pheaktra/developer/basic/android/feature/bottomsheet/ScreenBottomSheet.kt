package kh.com.pheaktra.developer.basic.android.feature.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.feature.radiobutton.OptionModel
import kh.com.pheaktra.developer.basic.android.feature.radiobutton.Size
import kh.com.pheaktra.developer.basic.android.feature.radiobutton.SugarLevel
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenBottomSheet(
    onBack: () -> Unit = {}
) {
    var isShowBottomSheet by remember { mutableStateOf(false) }
    var sugar by remember { mutableStateOf<OptionModel?>(null) }
    var size by remember { mutableStateOf<OptionModel?>(null) }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text(
                        text = "Bottom Sheet"
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
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                onClick = {
                    isShowBottomSheet = true
                }
            ) {
                Text("Order Now")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondaryContainer),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "Sugar is : ${sugar?.label}"
            )
            Text(
                text = "Size is : ${size?.label}"
            )

            if (isShowBottomSheet) {
                BottomSheetSelectOption(
                    onDismissRequest = {
                        isShowBottomSheet =  false
                    }
                ) { sugarResult, sizeResult ->
                    isShowBottomSheet = false
                    sugar = sugarResult
                    size = sizeResult
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetSelectOption(
    onDismissRequest: () -> Unit,
    onConfirm: (sugar: OptionModel, size: OptionModel) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val sugarList = listOf(
        OptionModel(id = SugarLevel.LEVEL_0.code, label = "No Sugar"),
        OptionModel(id = SugarLevel.LEVEL_25.code, label = "25%"),
        OptionModel(id = SugarLevel.LEVEL_50.code, label = "50%"),
        OptionModel(id = SugarLevel.LEVEL_75.code, label = "75%"),
        OptionModel(id = SugarLevel.LEVEL_100.code, label = "100%"),
    )
    val sizeList = listOf(
        OptionModel(id = Size.SMALL.code, label = "Small"),
        OptionModel(id = Size.MEDIUM.code, label = "Medium"),
        OptionModel(id = Size.LARGE.code, label = "Large"),
        OptionModel(id = Size.EXTRA_LARGE.code, label = "Extra Large"),
    )
    val (selectedSizeIndex, setSelectedSizeIndex) = remember { mutableIntStateOf(1) }
    val (selectedSugarIndex, setSelectedSugarIndex) = remember { mutableIntStateOf(1) }

    fun onClickConfirm() {
        val sugar = sugarList[selectedSugarIndex]
        val size = sizeList[selectedSizeIndex]
        onConfirm(sugar, size)
    }

    ModalBottomSheet(
//                    modifier = Modifier.fillMaxHeight(),
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        containerColor = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(8.dp),
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
//                        .wrapContentSize()
        ) {
            /**
             * Choose Sugar
             */
            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = "Select Sugar",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            sugarList.forEachIndexed { index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clickable(
                            onClick = {
                                setSelectedSugarIndex(index)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        onClick = {
                            setSelectedSugarIndex(index)
                        },
                        selected = index == selectedSugarIndex
                    )
                    Text(
                        text = item.label
                    )
                }
            }


            HorizontalDivider()
            /**
             * Selection Select Sze of drink
             */
            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = "Select Size",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            sizeList.forEachIndexed { index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clickable(
                            onClick = {
                                setSelectedSizeIndex(index)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        onClick = {
                            setSelectedSizeIndex(index)
                        },
                        selected = index == selectedSizeIndex
                    )
                    Text(
                        text = item.label
                    )
                }
                HorizontalDivider()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            onClick = {
                onClickConfirm()
            }
        ) {
            Text("Confirm")
        }
    }
}


@Preview(showBackground = false)
@Composable
fun ScreenBottomSheetPreview() {
    BaseTheme() {
        ScreenBottomSheet()
    }
}