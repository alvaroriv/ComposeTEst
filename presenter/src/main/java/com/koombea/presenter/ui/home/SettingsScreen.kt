@file:OptIn(ExperimentalMaterial3Api::class)

package com.koombea.presenter.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.koombea.androidtemplate.ui.theme.GrayBorder
import com.koombea.presenter.R
import com.koombea.presenter.ui.login.SettingsViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SettingsContent(settingsViewModel: SettingsViewModel) {
    val state by settingsViewModel.state.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp, 14.dp, 0.dp, 0.dp),verticalAlignment = Alignment.CenterVertically){
            Image(modifier = Modifier
                .padding(0.dp, 0.dp, 8.dp, 0.dp)
                .width(80.dp)
                .height(80.dp),
                painter = painterResource(id = R.mipmap.avatar05),
                contentDescription = ""
            )
            Column() {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text =  state.user?.email.toString(),
                        style = TextStyle(fontSize = 14.sp, color = GrayBorder, fontWeight = FontWeight.Bold), textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(text = state.user?.name.toString(),
                        style = TextStyle(fontSize = 24.sp, color = Color.Black), textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.weight(1f))
                }

            }
            Row(modifier = Modifier
                .border(width = 1.dp, color = Color(0xFFF1F1FA), shape = RoundedCornerShape(size = 8.dp))
            ) {
                Icon(
                    painter = painterResource(id = R.mipmap.sort),
                    contentDescription = "",
                    tint = Color.Black,
                )
            }
        }
        Spacer(modifier = Modifier.height(28.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)) {
            Text(
                text = "Currency",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF292B2D)
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "USD",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFF91919F)
                )
            )
            Icon(modifier = Modifier
                .height(20.dp)
                .padding(6.dp, 0.dp, 0.dp, 0.dp),
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "image description",
                tint = Color(0xFF7F3DFF)
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)) {
            Text(
                text = "Security",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF292B2D)
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Fingerprint",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFF91919F)
                )
            )
            Icon(modifier = Modifier
                .height(20.dp)
                .padding(6.dp, 0.dp, 0.dp, 0.dp),
                imageVector = Icons.Filled.ArrowForward,
                tint = Color(0xFF7F3DFF),
                contentDescription = "image description"
            )
        }
        Spacer(modifier = Modifier.height(63.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 16.dp), horizontalArrangement = Arrangement.Center) {
            Text(text = "Log out",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
        }
    }
}
@Composable
fun SettingsHeader(){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 16.dp), horizontalArrangement = Arrangement.Center) {
        Text(text = "Settings",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
    }
    Divider(color = Color.Gray, thickness = 1.dp)
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 16.dp, 16.dp, 0.dp),
        verticalArrangement = Arrangement.Top
    ) {
        SettingsHeader()
        SettingsContent(settingsViewModel)
    }

}