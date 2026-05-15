package kh.com.pheaktra.developer.basic.android.screen

import android.widget.Space
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.storage.accountList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHorizontalPager() {
    val accounts = accountList
    val pagerState = rememberPagerState(pageCount = { accounts.size })

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text("Horzontal Pager")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Icon more vert"
                        )
                    }
                }
            )
        },
    ) { padding ->
        HorizontalPager(
            modifier = Modifier
                .padding(padding)
                .wrapContentHeight(),
            state = pagerState,
            contentPadding = PaddingValues(16.dp),
            pageSpacing = 8.dp
        ) { page ->
            val account = accounts[page]
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.bg_account),
                    contentDescription = null,
                    modifier = Modifier.matchParentSize(), // 🔑 best for background
                    contentScale = ContentScale.Crop        // 🔑 fills Box properly
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween
                    ) {
                        Text(
                            text = account.accountName,
                            color = MaterialTheme.colorScheme.surface,
                            fontSize = 18.sp
                        )
                        Text(
                            text = account.accountType,
                            color = MaterialTheme.colorScheme.surface,
                            fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "Balance",
                        color = MaterialTheme.colorScheme.surface,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = account.currencyCode,
                            color = MaterialTheme.colorScheme.surface,
                            fontSize = 12.sp
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp),
                            text = account.balance.toString(),
                            color = MaterialTheme.colorScheme.surface,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Icon more vert"
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
fun ScreenHorizontalPagerPreview() {
    ScreenHorizontalPager()
}








