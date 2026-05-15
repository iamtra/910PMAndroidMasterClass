package kh.com.pheaktra.developer.basic.android.feature.topappbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.Red40
import org.jspecify.annotations.NullUnmarked

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopAppBar() {

    val isDialogOpen = remember { mutableStateOf(false) }

    if (isDialogOpen.value) {
        AlertDialog(
            title = {
                Text(
                    text = "Alert title",

                    )
            },
            onDismissRequest = {
                println("Click dismiss request")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        isDialogOpen.value = false
                    }
                ) {
                    Text(
                        text = "Ok",

                        )
                }
            },
//            modifier = TODO(),
            dismissButton = {
                FilledTonalButton(
                    onClick = {
                        isDialogOpen.value = false
                    }
                ) {
                    Text(
                        text = "Cancel",

                        )
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
            },
            text = {
                Text(
                    text = "This a simple dialog description. "
                )
            },
            shape = RoundedCornerShape(0.dp),
//            containerColor = TODO(),
//            iconContentColor = TODO(),
//            titleContentColor = TODO(),
//            textContentColor = TODO(),
//            tonalElevation = TODO(),
//            properties = TODO()
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            isDialogOpen.value = true
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_menu_24),
                            contentDescription = "Menu icom"
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(R.string.lbl_company_name),

                        )
                },

                actions = {
                    IconButton(
                        onClick = {
                            println("Ypu click navigation icon")
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_more_vert),
                            contentDescription = "Menu icom"
                        )
                    }
                    IconButton(
                        onClick = {
                            println("Ypu click navigation icon")
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_more_vert),
                            contentDescription = "Menu icom"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.error,
                    actionIconContentColor = MaterialTheme.colorScheme.error,
                    titleContentColor = MaterialTheme.colorScheme.error
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(paddingValues = padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Text("Hello 1")
                Spacer(modifier = Modifier.width(120.dp))
                Text("Hello 2")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenTopAppBarPreview() {
    ScreenTopAppBar()
}