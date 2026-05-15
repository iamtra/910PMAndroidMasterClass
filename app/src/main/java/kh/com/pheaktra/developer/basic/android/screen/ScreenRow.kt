package kh.com.pheaktra.developer.basic.android.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenRow() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(start = 24.dp),
                    text = "Hello Row !",
                    fontSize = 24.sp
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 8.dp),
                    text = "Hello Row !",
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ScreenRowPreview() {
    ScreenRow()
}