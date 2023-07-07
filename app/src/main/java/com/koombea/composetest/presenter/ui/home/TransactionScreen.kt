@file:OptIn(ExperimentalMaterial3Api::class)

package com.koombea.composetest.presenter.ui.home

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.koombea.composetest.R
import com.koombea.composetest.model.Routes

@Composable
fun ContentTransactionScreen(){
    Column( modifier = Modifier
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    }
    Spacer(modifier = Modifier.height(18.dp))
    Row(
        Modifier
            .fillMaxWidth()
            .height(34.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Today",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center)
    }
    Spacer(modifier = Modifier.height(24.dp))
    TransactionList()
    Spacer(modifier = Modifier.height(18.dp))
    Row(
        Modifier
            .fillMaxWidth()
            .height(34.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Yesterday",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center)
    }
    Spacer(modifier = Modifier.height(24.dp))
    TransactionList()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderTransactionScreen(){
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    Row() {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.border(width = 1.dp, color = Color(0xFFF1F1FA), shape = RoundedCornerShape(size = 40.dp))
            .width(96.dp)
            .height(40.dp)
            .padding(8.dp, 8.dp, 16.dp, 8.dp)
                .clickable { expanded = true }
        ) {
            Icon(
                painter = painterResource(id = R.mipmap.sort),
                contentDescription = "",
                tint = Color.Black,
            )
            Text(
                text = "Month",
                fontSize = 14.sp)
        }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Year") },
                    onClick = { }
                )
                DropdownMenuItem(
                    text = { Text("Week") },
                    onClick = {  }
                )
            }
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier
            .border(width = 1.dp, color = Color(0xFFF1F1FA), shape = RoundedCornerShape(size = 8.dp))
            .width(32.dp)
            .height(32.dp)
            .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.mipmap.sort),
                contentDescription = "",
                tint = Color.Black,
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun TransactionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 16.dp, 16.dp, 0.dp),
        verticalArrangement = Arrangement.Top
    ) {
        HeaderTransactionScreen()
        ContentTransactionScreen()
    }

}

@Composable
fun TransactionList() {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
       items(getTransactions()){ transaction ->
            ItemTransaction(transaction = transaction)
            { Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show() }
        }
    }
}