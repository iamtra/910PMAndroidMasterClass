package kh.com.pheaktra.developer.basic.android.feature.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.android.R
import kh.com.pheaktra.developer.basic.android.ui.theme.BaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenCheckBox() {
    data class CheckedModel(
        val id: Int,
        var checked: Boolean,
        val label: String,
        val enabled: Boolean,
    )

    var checkedList by remember {
        mutableStateOf(
            listOf(
                CheckedModel(
                    id = 1,
                    checked = false,
                    label = "Pickles",
                    enabled = true
                ),
                CheckedModel(
                    id = 2,
                    checked = false,
                    label = "Tomatoes",
                    enabled = true
                ),
                CheckedModel(
                    id = 3,
                    checked = false,
                    label = "Onions",
                    enabled = true
                ),
                CheckedModel(
                    id = 4,
                    checked = false,
                    label = "Cheese",
                    enabled = true
                ),
                CheckedModel(
                    id = 5,
                    checked = false,
                    label = "Lettuce",
                    enabled = true
                ),
                CheckedModel(
                    id = 6,
                    checked = false,
                    label = "Bacon",
                    enabled = true
                ),
                CheckedModel(
                    id = 7,
                    checked = false,
                    label = "Chicken",
                    enabled = true
                ),
                CheckedModel(
                    id = 8,
                    checked = false,
                    label = "Beef",
                    enabled = true
                ),
                CheckedModel(
                    id = 9,
                    checked = false,
                    label = "Mushrooms",
                    enabled = true
                ),
                CheckedModel(
                    id = 10,
                    checked = false,
                    label = "Avocado",
                    enabled = false
                )
            )
        )
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
                        text = "Meal Options"
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
//                    navigationIconContentColor = MaterialTheme.colorScheme.error,
//                    actionIconContentColor = MaterialTheme.colorScheme.error,
//                    titleContentColor = MaterialTheme.colorScheme.error
                )
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            checkedList.map { value ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clickable(
                            onClick = {
                                if (!value.enabled) {
                                    return@clickable
                                }

                                checkedList = checkedList.map { item ->
                                    if (item.id == value.id) {
                                        item.copy(checked = !item.checked)
                                    } else {
                                        item
                                    }
                                }
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = value.checked,
                        onCheckedChange = { isChecked ->
                            checkedList = checkedList.map { item ->
                                if (item.id == value.id) {
                                    item.copy(checked = isChecked)
                                } else {
                                    item
                                }
                            }
                        },
                        modifier = Modifier.size(48.dp),
                        enabled = value.enabled,
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primaryContainer,
                            uncheckedColor = MaterialTheme.colorScheme.secondary
                        ),
//                        interactionSource = interactionSource
                    )

                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = value.label,
                        textDecoration = if (value.checked) TextDecoration.LineThrough else null
                    )
                }
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ScreenCheckBoxPreview() {
    BaseTheme() {
        ScreenCheckBox()
    }
}