package com.koombea.presenter.ui.settings

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.koombea.data.character.base.model.User
import com.koombea.presenter.ui.login.SettingsViewModel
import com.koombea.presenter.ui.theme.textFieldLineColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun EditProfileScreen(settingsViewModel: SettingsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 32.dp, 16.dp, 0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state by settingsViewModel.state.collectAsStateWithLifecycle()
        var user = state.user

        val name = remember { mutableStateOf(TextFieldValue(user?.name.toString())) }
        val email = remember { mutableStateOf(user?.email.toString()) }
        val birthday = remember { mutableStateOf(TextFieldValue()) }
        val activity = (LocalContext.current as? Activity)
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.clickable {
                    activity?.finish()
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Edit Profile",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(64.dp))
        OutlinedTextField(
            label = { Text(text = "Name") },
            value = name.value,
            onValueChange = { name.value = it },
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldLineColor()
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            label = { Text(text = "Email") },
            value = email.value,
            onValueChange = {
                email.value = it },
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldLineColor()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            label = { Text(text = "Birthday") },
            value = birthday.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { birthday.value = it },
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldLineColor()
        )

        Spacer(modifier = Modifier.height(56.dp))
        Box() {
            Button(
                onClick = {
                    user?.name = name.value.text
                    user?.email = email.value
                    user?.birthday = birthday.value.text
                    settingsViewModel.editUser(user)
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Edit", style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}