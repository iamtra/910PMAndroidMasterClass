package kh.com.pheaktra.developer.basic.android.feature.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenCards(
    onBack: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    val isDialogOpen = remember { mutableStateOf(false) }
    var product by remember {
        mutableStateOf(
            ProductModel(
                id = "0001",
                productName = "Shoes",
                price = 120.0,
                size = 42.0f,
                color = "Blue",
                orderCount = 0,
                image = R.drawable.img_food_1
            )
        )
    }


    if (isDialogOpen.value) {
        AlertDialog(
            title = {
                Text(
                    text = "Alert title",

                    )
            },
            onDismissRequest = {
                println("Click dismiss request")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        isDialogOpen.value = false
                    }
                ) {
                    Text(
                        text = "Ok",

                        )
                }
            },
//            modifier = TODO(),
            dismissButton = {
                FilledTonalButton(
                    onClick = {
                        isDialogOpen.value = false
                    }
                ) {
                    Text(
                        text = "Cancel",

                        )
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
            },
            text = {
                Text(
                    text = "This a simple dialog description. "
                )
            },
            shape = RoundedCornerShape(0.dp),
//            containerColor = TODO(),
//            iconContentColor = TODO(),
//            titleContentColor = TODO(),
//            textContentColor = TODO(),
//            tonalElevation = TODO(),
//            properties = TODO()
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(R.string.lbl_card),
                    )
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(paddingValues = padding)
                .fillMaxSize()
                .verticalScroll(state = scrollState),
        ) {
            for (i in 1..20) {

                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp)
                    ,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                    shape = RoundedCornerShape(8.dp),
//                    elevation = CardDefaults.elevatedCardElevation(
//                        defaultElevation = 8.dp
//                    )
                    border = CardDefaults.outlinedCardBorder(
                        enabled = true
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .fillMaxHeight()
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(
                                    color = MaterialTheme.colorScheme.background
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier.size(40.dp),
                                painter = painterResource(product.image),
                                contentDescription = "",
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = product.productName,
                                    fontSize = 14.sp
                                )

                                IconButton(
                                    onClick = {}
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_delete),
                                        contentDescription = "Icon delete from card"
                                    )
                                }
                            }
                            Text(
                                modifier = Modifier.weight(1f),
                                text = product.priceDisplay,
                                fontSize = 12.sp
                            )
                            Text(
                                modifier = Modifier.weight(1f),
                                text = "Size: ${product.size}",
                                fontSize = 12.sp,
                            )
                            Row(
                                modifier = Modifier.wrapContentHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "Color: ${product.color}",
                                    fontSize = 14.sp
                                )

                                // Minus icon button
                                IconButton(
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
                                    ),
                                    onClick = {
                                        if (product.orderCount > 0) {
                                            product =
                                                product.copy(orderCount = product.orderCount - 1)
                                        }
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_minus),
                                        contentDescription = "Icon delete from card"
                                    )
                                }

                                Text(
                                    modifier = Modifier.size(24.dp),
                                    text = "${product.orderCount}",
                                    textAlign = TextAlign.Center
                                )

                                // Plus Icon button
                                IconButton(
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
                                    ),
                                    onClick = {
                                        product = product.copy(orderCount = product.orderCount + 1)
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_plus),
                                        contentDescription = "Icon delete from card"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenCardsPreview() {
    BaseTheme() {
        ScreenCards()
    }
}

data class ProductModel(
    val id: String,
    val productName: String,
    val price: Double,
    val size: Float,
    val color: String,
    var orderCount: Int,
    @DrawableRes val image: Int,
)

val ProductModel.priceDisplay get() = "$ $price"








