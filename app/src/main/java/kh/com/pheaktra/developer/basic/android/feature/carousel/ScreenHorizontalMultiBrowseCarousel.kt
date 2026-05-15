package kh.com.pheaktra.developer.basic.android.feature.carousel

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHorizontalMultiBrowseCarousel() {

    data class ItemModel(
        val id: Int,
        val title: String,
        val description: String,
        @DrawableRes val image: Int,
    )

    val list: List<ItemModel> = listOf(
//        ItemModel(id = 1, title = "Cat", description = "My cute cat", image = R.drawable.img_1),
//        ItemModel(id = 2, title = "Dog", description = "Friendly dog", image = R.drawable.img_2),
//        ItemModel(
//            id = 3,
//            title = "Bird",
//            description = "Small flying bird",
//            image = R.drawable.img_3
//        ),
        ItemModel(id = 4, title = "Car", description = "Fast sports car", image = R.drawable.img_4),
        ItemModel(
            id = 5,
            title = "Mountain",
            description = "Beautiful mountain view",
            image = R.drawable.img_5
        ),
        ItemModel(
            id = 6,
            title = "Laptop",
            description = "Powerful laptop",
            image = R.drawable.img_6
        ),
        ItemModel(
            id = 7,
            title = "Phone",
            description = "Modern smartphone",
            image = R.drawable.img_7
        ),
        ItemModel(
            id = 8,
            title = "Flower",
            description = "Colorful flower",
            image = R.drawable.img_8
        ),
        ItemModel(id = 9, title = "House", description = "Luxury house", image = R.drawable.img_9),
        ItemModel(
            id = 10,
            title = "Food",
            description = "Delicious food",
            image = R.drawable.img_10
        )
    )

    val carouselState = rememberCarouselState(
        initialItem = 0,
        itemCount = { list.size }
    )
    val carouselStateUncontained = rememberCarouselState(
        initialItem = 0,
        itemCount = { list.size }
    )
    Scaffold(
        modifier = Modifier.navigationBarsPadding(), topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "Carousel Compnent"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                )
            )
        }) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            HorizontalMultiBrowseCarousel(
                state = carouselState,
                preferredItemWidth = 186.dp,
                itemSpacing = 24.dp,
                flingBehavior = CarouselDefaults.singleAdvanceFlingBehavior(
                    state = carouselState,
                    snapAnimationSpec = spring()
                ),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
            ) { index ->
                val card = list[index]
                Box(
                    modifier = Modifier.wrapContentSize()
                ) {
                    Image(
                        modifier = Modifier,
                        painter = painterResource(card.image),
                        contentDescription = card.description
                    )
                }
            }

            HorizontalUncontainedCarousel(
                state = carouselStateUncontained,
                itemSpacing = 24.dp,
                itemWidth = 260.dp,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
            ) { index ->
                val card = list[index]
                Box(
                    modifier = Modifier.wrapContentSize()
                ) {
                    Image(
                        modifier = Modifier,
                        painter = painterResource(card.image),
                        contentDescription = card.description
                    )
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ScreenHorizontalMultiBrowseCarouselPreview() {
    BaseTheme() {
        ScreenHorizontalMultiBrowseCarousel()
    }
}