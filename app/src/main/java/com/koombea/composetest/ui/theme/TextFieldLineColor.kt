@file:OptIn(ExperimentalMaterial3Api::class)

package com.koombea.composetest.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun textFieldLineColor() : TextFieldColors {
    return TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = WhiteBorder,
        unfocusedLabelColor = GrayBorder
    )
}
