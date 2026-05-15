package kh.com.pheaktra.developer.basic.android.feature.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenDialog() {

    data class InvoiceItem(
        val label: String,
        val value: String
    )

    data class InvoiceSection(
        val title: String,
        val items: List<InvoiceItem>
    )

    val invoices = listOf(
        InvoiceSection(
            title = "General Info",
            items = listOf(
                InvoiceItem("Invoice No", "INV-0001"),
                InvoiceItem("Date", "17-03-2026"),
                InvoiceItem("Customer", "John Doe")
            )
        ),
        InvoiceSection(
            title = "Item Info",
            items = listOf(
                InvoiceItem("Item", "iPhone 15"),
                InvoiceItem("Quantity", "1"),
                InvoiceItem("Price", "$1200.00")
            )
        ),
        InvoiceSection(
            title = "Payment",
            items = listOf(
                InvoiceItem("Tax", "$120.00"),
                InvoiceItem("Total", "$1320.00")
            )
        )
    )

    var openDialog by remember { mutableStateOf(false) }
    var openFullScreenDialog by remember { mutableStateOf(true) }
    val invoice = """
    ============ INVOICE ============
    
    Invoice No              : INV-0001
    Date                    : 17-03-2026
    
    Customer                : John Doe
    Phone                   : 012345678
    
    -------------------------------------
    Item                    : iPhone 15
    Quantity                : 1
    Price                   : $1200.00
    -------------------------------------
    
    Subtotal                : $1200.00
    Tax (10%)               : $120.00
    Total                   : $1320.00
    
    Payment                 : Credit Card
    
    ======================================
    Thank You!
""".trimIndent()

    if (openDialog) {
        AlertDialog(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = "Example Icon"
                )
            },
            title = {
                Text(text = "Dialog Title")
            },
            text = {
                Text(text = "This is my alert dialog content")
            },
            onDismissRequest = {
//                openDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        println("Perform action base on actual task")
                        openDialog = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                    }
                ) {
                    Text("Dismiss")
                }
            },
            shape = RoundedCornerShape(0.dp)
        )
    }

    if (openFullScreenDialog) {

        Dialog(
            onDismissRequest = {
                openFullScreenDialog = false
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                usePlatformDefaultWidth = false
            ),
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.background

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    /**
                     * Dialog header
                     */
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(56.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "Invoice",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp,
                        )
                        IconButton(
                            onClick = {
                                openFullScreenDialog = false
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_plus),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.background
                            )
                        }
                    }

                    /**
                     * Dialog content
                     */
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        invoices.forEach { (title, items) ->
                            Box(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .fillMaxWidth()
                                    .background(
                                        color = MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0.5f
                                        )
                                    )
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(vertical = 8.dp)
                                        .padding(top = 8.dp),
                                    text = title,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.75f)
                                )
                            }
                            HorizontalDivider(Modifier.height(2.dp))

                            items.forEach { (label, value) ->
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                        .padding(horizontal = 16.dp)
                                ) {
                                    Text(
                                        modifier = Modifier.weight(1f),
                                        text = label,
                                    )
                                    Text(
                                        modifier = Modifier.weight(1f),
                                        text = value
                                    )
                                }
                                HorizontalDivider()
                            }
                        }

                    }


                    /**
                     * Dialog footer
                     */
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {

                        }
                    ) {
                        Text("Confirm")
                    }
                }
            }
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
                        text = "Alert Dialog"
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
            Row(
                Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        openDialog = true
                    }
                ) {
                    Text("Alert Dialog")
                }

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        openFullScreenDialog = true
                    }
                ) {
                    Text("Full Dialog")
                }
            }

        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = invoice
            )
        }

    }
}

@Preview(showBackground = false)
@Composable
fun ScreenDialogPreview() {
    BaseTheme() {
        ScreenDialog()
    }
}
