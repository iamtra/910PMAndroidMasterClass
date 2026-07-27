package kh.com.pheaktra.developer.basic.android.feature.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
fun ScreenChips(
    onBack: () -> Unit = {}
) {
    data class CheckedModel(
        val id: Int,
        var checked: Boolean,
        val label: String,
        val enabled: Boolean,
    )

    data class ChipsModel(
        val id: Int,
        val label: String,
        var selected: Boolean
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
    var isActive by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(false) }

    var chips by remember {
        mutableStateOf(
            listOf(
                ChipsModel(
                    id = 1,
                    label = "Red",
                    selected = false
                ),
                ChipsModel(
                    id = 2,
                    label = "Green",
                    selected = false
                ),
                ChipsModel(
                    id = 3,
                    label = "Blue",
                    selected = false
                ),
                ChipsModel(
                    id = 4,
                    label = "Yellow",
                    selected = false
                ),
                ChipsModel(
                    id = 5,
                    label = "Orange",
                    selected = false
                ),
                ChipsModel(
                    id = 6,
                    label = "Purple",
                    selected = false
                ),
                ChipsModel(
                    id = 7,
                    label = "Pink",
                    selected = false
                ),
                ChipsModel(
                    id = 8,
                    label = "Black",
                    selected = false
                ),
                ChipsModel(
                    id = 9,
                    label = "White",
                    selected = false
                ),
                ChipsModel(
                    id = 10,
                    label = "Brown",
                    selected = false
                ),
                ChipsModel(
                    id = 11,
                    label = "Gray",
                    selected = false
                ),
                ChipsModel(
                    id = 12,
                    label = "Cyan",
                    selected = false
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
                        text = "Chips"
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
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp)
            ) {
                AssistChip(
                    modifier = Modifier
                        .weight(1f),
                    onClick = {
                        isActive = !isActive
                    },
                    label = {
                        Text(
                            text = "Hello word !"
                        )
                    },
                    leadingIcon = {
                        if (isActive) {
                            Icon(
                                painter = painterResource(R.drawable.ic_check),
                                contentDescription = ""
                            )
                        }
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_delete),
                            contentDescription = ""
                        )
                    },
                    enabled = true,
                    shape = RoundedCornerShape(0.dp),
                    elevation = AssistChipDefaults.assistChipElevation(
                        elevation = 0.dp,
                        pressedElevation = 12.dp
                    ),
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        trailingIconContentColor = MaterialTheme.colorScheme.error,
                        leadingIconContentColor = MaterialTheme.colorScheme.background,
                        disabledLabelColor = MaterialTheme.colorScheme.secondary,
                        disabledLeadingIconContentColor = MaterialTheme.colorScheme.inversePrimary,
                        disabledTrailingIconContentColor = MaterialTheme.colorScheme.background.copy(
                            alpha = 06f
                        )
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                AssistChip(
                    modifier = Modifier
                        .weight(1f),
                    onClick = {
                        selected = !selected
                    },
                    label = {
                        Text(
                            text = "Hello word !"
                        )
                    },
                    leadingIcon = if (selected) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    },
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(chips.size) { index ->
                    FilterChip(
                        selected = selected,
                        onClick = {
                            selected = !selected
                        },
                        label = {
                            Text(
                                text = "Red"
                            )
                        },
                        leadingIcon = if (selected) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Done icon",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        },
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
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
fun ScreenChipsPreview() {
    BaseTheme() {
        ScreenChips()
    }
}

/**
 * homework and researching
 * 1. Create a model for chips
 * 2. Create chips list
 * 3. Create 3 horizontal scroll of chips list
 *      - AssistChips
 *      - FilterChip (Add logic when user click item show and hide tick icon)
 *      - InputChip
 */