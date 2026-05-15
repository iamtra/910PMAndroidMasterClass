package kh.com.pheaktra.developer.basic.android.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.font.fontFamily

@Composable
fun ScreenLazyHorizontalGrid() {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyHorizontalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(192.dp + 16.dp + 24.dp),
                rows = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(30) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(
                                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(R.drawable.img_food_2),
                            contentDescription = "",
                        )

                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "Dark Paradise",
                                fontSize = 16.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Bold
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = ""
                                )
                                Text(
                                    text = "Lana Del Rey - 150M Plays",
                                    fontSize = 16.sp,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight.Light
                                )
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_more_vert),
                                contentDescription = ""
                            )

                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ScreenLazyHorizontalGridPreview() {
    ScreenLazyHorizontalGrid()
}

/**
 * Homework
 * - Create row
 * - Box
 */









