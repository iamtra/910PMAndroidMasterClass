package kh.com.pheaktra.developer.basic.android.feature.filledbutton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenFilledButton() {
    var text by remember { mutableStateOf("Hello world !") }
    var isChecked by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            MediumTopAppBar(
                navigationIcon = {
                    FilledTonalIconButton(
                        onClick = {
                            println("Button is disabled")
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_menu_24),
                            contentDescription = ""
                        )
                    }
                },
                title = {
                    Text(
                        text = "Filled Buttons"
                    )
                },
                actions = {
                    FilledTonalIconButton(
                        onClick = {
                            println("Button is disabled")
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_share),
                            contentDescription = ""
                        )
                    }
                    FilledTonalIconButton(
                        onClick = {
                            println("Button is disabled")
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_more_vert),
                            contentDescription = ""
                        )
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                )
            )
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
                text = text
            )
            Spacer(Modifier.height(56.dp))
            Button(
                modifier = Modifier
                    .height(56.dp),
                onClick = {
                    text = ""
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = Color.Gray,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                ),
                enabled = true
            ) {
                Row(
                    modifier = Modifier.wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_share),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Remove Text"
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            FilledTonalButton(
                onClick = {
                    text = "Hello new text"
                }
            ) {
                Row(
                    modifier = Modifier.wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Add Text"
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(R.drawable.ic_share),
                        contentDescription = ""
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            FilledTonalIconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_share),
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            FilledTonalIconToggleButton(
                checked = isChecked,
                onCheckedChange = { value ->
                    println("====> $value")
                    isChecked = value
                },
                colors = IconButtonDefaults.filledIconToggleButtonColors(
                    contentColor = if (isChecked) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondary
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_check),
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                onClick = {}
            ) {
                Text(
                    text = "Cancel"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            ElevatedButton(
                onClick = {},
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 16.dp
                )
            ) {
                Text(
                    text = "Ok"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                onClick = {}
            ) {
                Text(
                    text = "Close"
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ScreenFilledButtonPreview() {
    ScreenFilledButton()
}