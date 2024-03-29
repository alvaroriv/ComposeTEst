package com.koombea.composetest.presenter.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.koombea.presenter.ui.theme.textFieldLineColor

@Composable
fun HeaderAddScreen() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalArrangement = Arrangement.Center) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "",
            tint = Color.White
        )
        Text(
            text = "Expense", modifier = Modifier.weight(1f),
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White),
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentAddScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(75.dp))
        Text(
            text = "How much?",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xffFCFCFC)
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(13.dp))
        Text(
            text = "$0",
            style = TextStyle(
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xffFCFCFC)
            ),
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    )
    {
        Spacer(modifier = Modifier.height(16.dp))
        var selectedText by remember { mutableStateOf("") }
        var expanded by remember { mutableStateOf(false) }
        var expanded2 by remember { mutableStateOf(false) }
        Column() {
            OutlinedTextField(value = "Category", onValueChange = {selectedText = it},
                enabled = false,
                readOnly = true,
                modifier = Modifier
                    .clickable { expanded = true }
                    .fillMaxWidth())
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                DropdownMenuItem(
                    text = { Text("Food") },
                    onClick = { }
                )
                DropdownMenuItem(
                    text = { Text("Games") },
                    onClick = {  }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Description") },
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldLineColor()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column() {
            OutlinedTextField(value = "Wallet", onValueChange = {selectedText = it},
                enabled = false,
                readOnly = true,
                modifier = Modifier
                    .clickable { expanded2 = true }
                    .fillMaxWidth())
            DropdownMenu(
                expanded = expanded2,
                onDismissRequest = { expanded2 = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                DropdownMenuItem(
                    text = { Text("Main") },
                    onClick = { }
                )
                DropdownMenuItem(
                    text = { Text("Secondary") },
                    onClick = {  }
                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp), horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier.size(26.dp)
            )
            Text(
                text = "Add attachment",
                style = TextStyle(fontSize = 18.sp, color = Color(0xff91919F)),
                textAlign = TextAlign.Center
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp,0.dp,16.dp,16.dp)) {
            Column() {
                Text(
                    text = "Reminder",
                    style = TextStyle(fontSize = 16.sp, color = Color(0xFF292B2D)),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Set a transaction reminder",
                    style = TextStyle(fontSize = 13.sp, color = Color(0xFF91919F)),
                    textAlign = TextAlign.Center
                )
            }
            val isOn = remember{ mutableStateOf(false) }
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isOn.value,
                onCheckedChange = { newState -> isOn.value = newState },
                enabled = false
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "Continue", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(64.dp))
    }
}
    @Preview(showSystemUi = true)
    @Composable
    fun AddScreen() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xffFD3C4A))
                .verticalScroll(rememberScrollState())
        ) {
            HeaderAddScreen()
            ContentAddScreen()
        }

    }