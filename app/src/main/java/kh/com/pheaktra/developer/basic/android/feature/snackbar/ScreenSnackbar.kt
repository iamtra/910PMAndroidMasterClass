package kh.com.pheaktra.developer.basic.android.feature.snackbar

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSnackbar() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
//        snackbarHostState.showSnackbar("Screen snack bar launch successfully.")
    }


    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
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
            Button(
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Message")
                    }
                }
            ) {
                Text("Show Snackbar")
            }
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    coroutineScope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Message",
                            actionLabel = "Undo",
                            withDismissAction = true,
                            duration = SnackbarDuration.Indefinite
                        )

                        when(result) {
                            SnackbarResult.ActionPerformed -> {
                                println("Action performed")
                            }
                            SnackbarResult.Dismissed -> {
                                println("Dismissed")
                            }
                        }
                    }
                }
            ) {
                Text("Show Snackbar with action")
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                       val toast = Toast.makeText(context, "Toast Message", Toast.LENGTH_LONG)
                        toast.show()
                    }
                }
            ) {
                Text("Show Toast Message")
            }
        }
    }
}


@Preview(showBackground = false)
@Composable
fun ScreenSnackbarPreview() {
    BaseTheme() {
        ScreenSnackbar()
    }
}