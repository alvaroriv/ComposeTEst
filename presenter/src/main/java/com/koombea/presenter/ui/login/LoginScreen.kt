@file:OptIn(ExperimentalMaterial3Api::class)

package com.koombea.composetest.presenter.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.koombea.androidtemplate.ui.theme.GrayBorder
import com.koombea.androidtemplate.ui.theme.WhiteBorder
import com.koombea.presenter.model.Routes
import com.koombea.presenter.ui.theme.textFieldLineColor

@Composable
fun CustomTextField(field: String, name: String, onTextChanged: (String) -> Unit){
    OutlinedTextField(
        label = { Text(text = name) },
        value = field,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = WhiteBorder,
            unfocusedLabelColor = GrayBorder
        )
    )
}

@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 32.dp, 16.dp, 0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val email = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        Text(text = "Login", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(64.dp))
        OutlinedTextField(
            label = { Text(text = "Email") },
            value = email.value,
            onValueChange = { email.value = it },
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldLineColor()
        )

        Spacer(modifier = Modifier.height(14.dp))
        OutlinedTextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it },
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldLineColor()
        )

        Spacer(modifier = Modifier.height(56.dp))
        Button(
            onClick = {navController.navigate(Routes.Dashboard.route)  },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "Sign in", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.height(36.dp))
        ClickableText(
            text = AnnotatedString("Create Account"),
            onClick = { navController.navigate(Routes.SignUp.route) },
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}