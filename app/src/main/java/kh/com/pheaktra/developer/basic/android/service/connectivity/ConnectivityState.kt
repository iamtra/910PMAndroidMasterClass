package kh.com.pheaktra.developer.basic.android.service.connectivity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import kh.com.pheaktra.developer.basic.android.util.extension.ConnectionState
import kh.com.pheaktra.developer.basic.android.util.extension.observeConnectivityAsFlow

@Composable
fun connectivityState(): State<ConnectionState> {
    val context = LocalContext.current
    return produceState(initialValue = ConnectionState.Available) {
        context.observeConnectivityAsFlow().collect {
            value = it
        }
    }
}