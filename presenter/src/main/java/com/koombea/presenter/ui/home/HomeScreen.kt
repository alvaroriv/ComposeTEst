@file:OptIn(ExperimentalMaterial3Api::class)

package com.koombea.presenter.ui.home

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.koombea.androidtemplate.ui.theme.BackgrounItemTransaction
import com.koombea.androidtemplate.ui.theme.GrayBorder
import com.koombea.androidtemplate.ui.theme.GreenContainer
import com.koombea.androidtemplate.ui.theme.RedContainer
import com.koombea.data.character.base.model.Transaction
import com.koombea.presenter.R
import com.koombea.presenter.ui.login.TransactionViewModel
import com.koombea.presenter.ui.settings.rememberLifecycleEvent

@Composable
fun Content(transactionViewModel: TransactionViewModel){
    val lifecycleEvent = rememberLifecycleEvent()
    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            transactionViewModel.getTransactions()
        }
    }

    Column( modifier = Modifier
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(23.dp))
        Text(text = "Account Balance",
            style = TextStyle(fontSize = 14.sp, color = GrayBorder))
        Text(text = "$9400",
            style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(27.dp))
        Row(modifier = Modifier
            .fillMaxWidth()) {
            Row(modifier = Modifier
                .background(color = GreenContainer, shape = RoundedCornerShape(28.dp))
                .weight(1f)
                .padding(16.dp)) {
                Image(
                    painter = painterResource(id = R.mipmap.group222),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column() {
                    Text(text = "Income",
                        style = TextStyle(fontSize = 14.sp, color = Color.White))
                    Text(text = "$5000",
                        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row(modifier = Modifier
                .background(color = RedContainer, shape = RoundedCornerShape(28.dp))
                .weight(1f)
                .padding(16.dp)) {
                Image(
                    painter = painterResource(id = R.mipmap.group223),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column() {
                    Text(text = "Expenses",
                        style = TextStyle(fontSize = 14.sp, color = Color.White))
                    Text(text = "$12000",
                        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White))
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(36.dp))
    Text(text = "Spend Frequency",
        style = TextStyle(fontSize = 18.sp, color = Color.Black, fontWeight = FontWeight.Bold))
    Spacer(modifier = Modifier.height(30.dp))
    Row(
        Modifier
            .fillMaxWidth()
            .height(34.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Today",
            style = TextStyle(fontSize = 14.sp, color = Color(0xffFCAC12)),modifier = Modifier
                .weight(1f)
                .background(color = Color(0xffFCEED4), shape = RoundedCornerShape(16.dp)), textAlign = TextAlign.Center)
        Text(text = "Week",
            style = TextStyle(fontSize = 14.sp),modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text(text = "Month",
            style = TextStyle(fontSize = 14.sp),modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text(text = "Year",
            style = TextStyle(fontSize = 14.sp),modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
    }
    Spacer(modifier = Modifier.height(18.dp))
    Row(
        Modifier
            .fillMaxWidth()
            .height(34.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Recent Transaction",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "See All",
            style = TextStyle(fontSize = 14.sp, color = Color(0xff7F3DFF)),
            modifier = Modifier
                .background(color = Color(0xffEEE5FF), shape = RoundedCornerShape(16.dp))
                .padding(10.dp, 0.dp, 10.dp, 0.dp), textAlign = TextAlign.Center)
    }
    Spacer(modifier = Modifier.height(24.dp))
    RecentTransactionList(transactionViewModel)
}
@Composable
fun Header(navController: NavHostController){
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.mipmap.avatar05),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "October",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.weight(1f))
        val context = LocalContext.current
        TextButton(onClick = { context.startActivity(Intent(context, AddTransactionActivity::class.java)) }) {
            Image(
                painter = painterResource(id = R.mipmap.add),
                contentDescription = ""
            )
        }
    }
}

@Composable
//@Preview(showSystemUi = true)
fun HomeScreen(navController: NavHostController, transactionViewModel: TransactionViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 16.dp, 16.dp, 0.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Header(navController)
        Content(transactionViewModel)
    }

}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun RecentTransactionList(transactionViewModel: TransactionViewModel) {
    val state by transactionViewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(state.transactions){transaction ->
            ItemTransaction(transaction = transaction)
            { Toast.makeText(context, it.description, Toast.LENGTH_SHORT).show() }
        }
    }
}

@Composable
fun ItemTransaction(transaction: Transaction, onItemSelected: (Transaction) -> Unit) {
    Row(Modifier.fillMaxWidth()
        .padding(10.dp, 10.dp, 10.dp, 0.dp)
        .height(89.dp)
        .background(BackgrounItemTransaction, shape = RoundedCornerShape(24.dp))
        .clickable { onItemSelected(transaction) }, verticalAlignment = Alignment.CenterVertically){
        Image(
            painter = painterResource(id = R.mipmap.group223),
            contentDescription = ""
        )
        Column() {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text =  transaction.category,
                    style = TextStyle(fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Bold), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.weight(1f))
                Text(text =  "$ "+transaction.value,
                    style = TextStyle(fontSize = 16.sp, color = Color.Red), textAlign = TextAlign.Center)
            }
            Spacer(modifier = Modifier.height(13.dp))
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = transaction.description,
                    style = TextStyle(fontSize = 13.sp, color = GrayBorder), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = transaction.dateCreated,
                    style = TextStyle(fontSize = 13.sp, color = GrayBorder), textAlign = TextAlign.Center)
            }

        }
    }
}