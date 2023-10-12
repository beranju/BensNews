package com.beran.bensnews.ui.screen.explore.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beran.bensnews.R
import com.beran.bensnews.ui.theme.BensNewsTheme


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(
    searchText: String,
    onChangeSearchText: (String) -> Unit,
    onSearchQuery: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyBoardController = LocalSoftwareKeyboardController.current
    Column(modifier = modifier) {
        OutlinedTextField(
            value = searchText,
            onValueChange = onChangeSearchText,
            placeholder = { Text(text = stringResource(R.string.search_hint)) },
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search Icon")
            },
            trailingIcon = {
                if (searchText.isNotBlank()) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Cancel Icon",
                        modifier = Modifier.clickable { onChangeSearchText("") })
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(30.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchQuery()
                    keyBoardController?.hide()
                }
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchAppBarPrev() {
    BensNewsTheme {
        SearchAppBar(searchText = "", onChangeSearchText = {}, onSearchQuery = {})
    }
}