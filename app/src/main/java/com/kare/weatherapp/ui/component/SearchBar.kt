package com.kare.weatherapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kare.weatherapp.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(onSearch: (String) -> Unit, onInputError: () -> Unit) {
    var inputText by remember { mutableStateOf("") }
    var isInputError by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = inputText,
                onValueChange = {
                    inputText = it
                    isInputError = false
                },
                label = {
                    Text(text = stringResource(id = R.string.search_hint))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if (inputText.isNotBlank()) {
                            onSearch(inputText)
                            keyboardController?.hide()
                        } else {
                            isInputError = true
                            onInputError()
                        }
                    }
                ),
                isError = isInputError,
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorContainerColor = Color.Red.copy(0.1f),
                    errorIndicatorColor = Color.Transparent,
                    errorLabelColor = Color.Red,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    errorTrailingIconColor = Color.Red,
                    focusedTrailingIconColor = Color.Black,
                    unfocusedTrailingIconColor = Color.Black,
                    errorCursorColor = Color.Red,
                    cursorColor = Color.Black,
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.Black
                )
            )
        }
    }

    if (isInputError) {
        Text(
            text = stringResource(id = R.string.no_input_found),
            color = Color.Red,
            modifier = Modifier.padding(start = 26.dp),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SearchBarPreview() {
    SearchBar(
        onSearch = { },
        onInputError = { }
    )
}