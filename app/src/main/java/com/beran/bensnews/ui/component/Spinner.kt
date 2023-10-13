package com.beran.bensnews.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize
import com.beran.bensnews.ui.theme.BensNewsTheme
import com.beran.bensnews.utils.Sort

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Spinner(
    selected: String,
    onChangeSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var size by remember {
        mutableStateOf(androidx.compose.ui.geometry.Size.Zero)
    }
    Column(modifier = modifier) {
        TextField(
            value = selected,
            onValueChange = onChangeSelected,
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.ArrowDropUp else
                        Icons.Default.ArrowDropDown, contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colorScheme.onSurface,
                disabledTextColor = Color.Transparent,
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinate ->
                    size = coordinate.size.toSize()
                }
        )

        DropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(
                with(LocalDensity.current) { size.width.toDp() }
            )) {
            Sort.values().toList().forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = it.name)
                    },
                    onClick = {
                        onChangeSelected(it.value)
                        expanded = false
                    })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SpinnerPrev() {
    BensNewsTheme {
        Spinner(
            selected = "",
            onChangeSelected = {}
        )
    }
}