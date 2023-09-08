@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)

package com.koombea.presenter.ui.home

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.koombea.presenter.R
import com.koombea.presenter.ui.login.TransactionViewModel

@Composable
fun ContentTransactionScreen(transactionViewModel: TransactionViewModel){
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
    TransactionList(transactionViewModel)
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
    TransactionList(transactionViewModel)
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
fun TransactionScreen(transactionViewModel: TransactionViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 16.dp, 16.dp, 0.dp),
        verticalArrangement = Arrangement.Top
    ) {
        HeaderTransactionScreen()
        ContentTransactionScreen(transactionViewModel)
    }

}

@Composable
fun TransactionList(transactionViewModel: TransactionViewModel) {
    val state by transactionViewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
       items(state.transactions){ transaction ->
            ItemTransaction(transaction = transaction)
            { Toast.makeText(context, it.description, Toast.LENGTH_SHORT).show() }
        }
    }
}