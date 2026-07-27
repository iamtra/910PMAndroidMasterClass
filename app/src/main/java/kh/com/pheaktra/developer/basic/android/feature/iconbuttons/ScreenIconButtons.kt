package kh.com.pheaktra.developer.basic.android.feature.iconbuttons

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kh.com.pheaktra.developer.basic.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenIconButtons(
    onBack: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {

            MediumTopAppBar(
                navigationIcon = {
                    FilledTonalIconButton(
                        onClick = {
                            onBack()
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
                        text = "Icon Buttons"
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
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    // Do something
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface
                ),
                interactionSource = interactionSource
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_share),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            FilledIconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_share),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.weight(1f))
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
            Spacer(modifier = Modifier.weight(1f))
            OutlinedIconButton(
                onClick = {
                    println("Button is disabled")
                },
                enabled = false
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_share),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ScreenIconButtonsPreview() {
    ScreenIconButtons()
}


/**
 * Homework
 * Create 4 different icon buttons with different color
 */