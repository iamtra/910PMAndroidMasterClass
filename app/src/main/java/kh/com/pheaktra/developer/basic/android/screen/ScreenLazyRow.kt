package kh.com.pheaktra.developer.basic.android.screen

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenLazyRow() {
    val list = listOf(Color.Red, Color.Cyan, Color.Black, Color.DarkGray)
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onSecondaryContainer
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            item {
                LazyRow(
                    modifier = Modifier
                        .height(250.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    contentPadding = PaddingValues(16.dp)
                ) {

                    items(list.size) { index ->
                        Column(
                            modifier = Modifier
                                .size(width = 320.dp, height = 200.dp)
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp))
                                .background(color = list[index]),
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .background(color = MaterialTheme.colorScheme.background),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.padding(start = 8.dp),
                                    text = "Buon Pheakrea | 202024444",
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .background(color = MaterialTheme.colorScheme.background),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(color = Color.LightGray),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Top-Up",
                                    )
                                }
                                VerticalDivider(color = Color.Red)
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(color = Color.LightGray),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Payment",
                                    )
                                }
                                VerticalDivider(color = Color.Red)
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(color = Color.LightGray),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Transfer",
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

            items(20) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                        .height(64.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.Cyan.copy(alpha = 0.5f))
                ) {

                }

            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ScreenLazyRowPreview() {
    ScreenLazyRow()
}

/**
 * Homework
 * Design any Ui
 * - Use lazy row to create horizontal scroll
 */









