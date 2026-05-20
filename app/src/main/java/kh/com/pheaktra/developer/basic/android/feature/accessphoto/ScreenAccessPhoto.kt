package kh.com.pheaktra.developer.basic.android.feature.accessphoto

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAccessPhoto() {

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val pickMedia = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            imageUri = uri
            Log.d("PhotoPicker", "Selected URI: $uri")
        } else {
            Log.d("PhotoPicker", "No media selected")
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
                        text = "Screen Photo"
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
        bottomBar = {
            Button(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                onClick = {
                    // Click to open media picker
                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
            ) { Text("Select Photo") }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = imageUri,
                contentDescription = "Image"
            )
        }
    }
}


@Preview(showBackground = false)
@Composable
fun ScreenToolTipsPreview() {
    BaseTheme() {
        ScreenAccessPhoto()
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