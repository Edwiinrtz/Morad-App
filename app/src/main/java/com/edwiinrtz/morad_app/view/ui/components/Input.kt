package com.edwiinrtz.morad_app.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.edwiinrtz.morad_app.view.ui.theme.InputBackground


@Composable
fun Input(label: String, text: String, onChange: (String) -> Unit) {
    //var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    var passwordVisibility: Boolean by remember { mutableStateOf(label.lowercase() != "contraseña") }
    TextField(value = text, singleLine = true, onValueChange = {

        if(label.lowercase() == "email"){
            onChange(it.trim())
        }else{
            onChange(it)
        }
    },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone =  { focusManager.clearFocus() }),

        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),

        modifier = Modifier
            .fillMaxWidth(.8f)
            .padding(0.dp, 10.dp)
            .background(color = InputBackground, shape = RoundedCornerShape(10.dp)),
        label = { Text(text = label, color = Color(20, 20, 20, 128)) }
    )

}

@Preview(showBackground = true)
@Composable
fun InputPreview() {
    Input(label = "Email", "") {}

}


