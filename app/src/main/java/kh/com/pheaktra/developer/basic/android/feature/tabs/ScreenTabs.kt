package kh.com.pheaktra.developer.basic.android.feature.tabs

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
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
fun ScreenTabs(
    onBack: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    var selectedTabIndex by remember { mutableIntStateOf(TabIndex.Overview.index) }

    val tabs = listOf(
        TabModel(label = "Overview", icon = R.drawable.ic_menu_24),
        TabModel(label = "Specification", icon = R.drawable.ic_menu_24)
    )
    val pagerState = rememberPagerState(pageCount = { tabs.size })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { page ->
                selectedTabIndex = page
            }
    }


    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_menu_24),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "Tab Component"
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
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /**
             * Test more
             * 1. rimaryTabRow
             * 2. SecondaryTabRow
             */
            TabHeader(selectedTabIndex, tabs) {
                selectedTabIndex = it

                scope.launch {
                    pagerState.animateScrollToPage(
                        page = selectedTabIndex,
                        animationSpec = TweenSpec(500)
                    )
                }
            }

            HorizontalPager(
                modifier = Modifier
                    .weight(1f),
                state = pagerState,
                contentPadding = PaddingValues(16.dp),
                pageSpacing = 8.dp
            ) {
                when (selectedTabIndex) {
                    TabIndex.Overview.index -> {
                        OverviewContent(modifier = Modifier.weight(1f))
                    }

                    TabIndex.Specification.index -> {
                        SpecificationContent(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabHeader(selectedTabIndex: Int, tabs: List<TabModel>, onClick: (Int) -> Unit) {
    /**
     * Test more
     * 1. rimaryTabRow
     * 2. SecondaryTabRow
     */
    SecondaryTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        for (index in tabs.indices) {
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    onClick(index)
                },
                text = {
                    Text(text = tabs[index].label)
                },
//            icon = {
//                Icon(
//                    painter = painterResource(tabs[index].icon),
//                    contentDescription = null
//                )
//            }
            )
        }
    }
}

@Composable
fun OverviewContent(modifier: Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(100) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "${it + 1}. This content overview $it"
            )
            HorizontalDivider()
        }
    }
}

@Composable
fun SpecificationContent(modifier: Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(100) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "${it + 1}. This content specification $it"
            )
            HorizontalDivider()
        }
    }
}

data class TabModel(
    val label: String,
    @DrawableRes val icon: Int,
)

enum class TabIndex(val index: Int) {
    Overview(0),
    Specification(1)
}


@Preview(showBackground = false)
@Composable
fun ScreenTabsPreview() {
    BaseTheme() {
        ScreenTabs()
    }
}