package kh.com.pheaktra.developer.basic.android.feature.tooltips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.model.TooltipViewModel
import kh.com.pheaktra.developer.basic.android.model.ReceiverAccountModel
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenToolTips() {
    val state = rememberTooltipState()
    val scope = rememberCoroutineScope()

    val tooltipViewModel = TooltipViewModel()
    val accountInfo by tooltipViewModel.receiverAccount.collectAsStateWithLifecycle()
    var receiverAccount by remember { mutableStateOf<ReceiverAccountModel?>(null) }


    LaunchedEffect(accountInfo) {
        accountInfo.let {
            if (it != null) {
                receiverAccount = accountInfo
                println("===> $receiverAccount")
            }
        }
    }


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
                        text = "ToolTips"
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
                .background(MaterialTheme.colorScheme.primaryContainer),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pay to"
                )
                TooltipBox(
                    positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
                    tooltip = {
                        TooltipsContent(receiverAccount)
                    },
                    state = state,
                    modifier = Modifier,
                    focusable = true,
                    enableUserInput = true
                ) {
                    IconButton(
                        onClick = {
                            scope.launch {
                                tooltipViewModel.getAccountInfo()
                                delay(1000)
                                state.show()
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_info),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TooltipsContent(data: ReceiverAccountModel?) {
    val info = """
        Account Name:       ${data?.accountName}
        Account Number:     ${data?.accountNumber}
        Receiver Bank Name: ${data?.receiverBankName}
    """.trimIndent()
    Box(modifier = Modifier
        .wrapContentSize()
        .background(
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(8.dp)
        )
    ) {
        Text(text = info)
    }
}


@Preview(showBackground = false)
@Composable
fun ScreenToolTipsPreview() {
    BaseTheme() {
        ScreenToolTips()
    }
}

/**
 * Exercise for tooltips
 *  - Create simple tool tip with text
 *  - Customer color, add image, change shape and ...
 *  - Manual action (dismiss, click outsize of tooltip to close)
 *  - Use it with long press, press and hold to show
 *  - Add view model and practice
 *
 */