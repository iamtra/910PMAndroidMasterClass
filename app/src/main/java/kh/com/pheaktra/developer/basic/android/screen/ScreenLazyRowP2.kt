package kh.com.pheaktra.developer.basic.android.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.data.base.foodList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenLazyRowP2() {
    val foods = foodList
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text("LazyRow")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        floatingActionButton = {

        },
        bottomBar = {

        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues = padding)
        ) {
            item {
                LazyRow() {
                    items(foods.size) { index ->
                        Box(
                            modifier = Modifier
                                .size(width = 120.dp, height = 130.dp)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            colorResource(R.color.purple_200),
                                            Color(0xFF2196F3), // Blue
                                            Color(0xFF21CBF3)  // Light Blue
                                        )
                                    )
                                )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(shape = RoundedCornerShape(8.dp)),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    modifier = Modifier.size(100.dp),
                                    painter = painterResource(foods[index].image),
                                    contentDescription = foods[index].label,
                                )

                                Text(
                                    text = "Breakfast",
                                    fontSize = 16.sp
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .padding(start = 8.dp, top = 8.dp)
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(color = Color.Red),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${foods[index].id}"
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ScreenLazyRowP2Preview() {
    ScreenLazyRowP2()
}