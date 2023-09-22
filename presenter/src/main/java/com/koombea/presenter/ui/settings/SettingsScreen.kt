package com.koombea.presenter.ui.settings

import android.content.Intent
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.koombea.androidtemplate.ui.theme.GrayBorder
import com.koombea.data.character.base.SharedPrefUtils
import com.koombea.presenter.R
import com.koombea.presenter.ui.login.SettingsViewModel
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class, ExperimentalLifecycleComposeApi::class)
fun ModalBottomSheetSample(settingsViewModel: SettingsViewModel) {
    val stateModal = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val state by settingsViewModel.state.collectAsStateWithLifecycle()
    ModalBottomSheetLayout(
        sheetState = stateModal,
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 24.dp))
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "Log out",
                    style = TextStyle(fontSize = 18.sp, color = Color(0xFF000000), textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Are you sure you want to log out?",
                    style = TextStyle(fontSize = 16.sp, color = Color(0xFF91919F), textAlign = TextAlign.Center))
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()) {
                    Button(onClick = { scope.launch { stateModal.hide() } },
                    modifier = Modifier.fillMaxWidth().weight(1f).height(56.dp).background(color = Color(0xFFEEE5FF)),
                    shape = RoundedCornerShape(16.dp)) {
                        Text(text = "No", style = TextStyle(
                            fontSize = 18.sp), color = Color(0xFFEEE5FF))
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { settingsViewModel.signOut(state.user!!) },
                        modifier = Modifier.fillMaxWidth().weight(1f).height(56.dp),
                        shape = RoundedCornerShape(16.dp)) {
                        Text(text = "Yes", style = TextStyle(
                            fontSize = 18.sp), color = Color(0xFF7F3DFF))
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 16.dp, 16.dp, 0.dp),
            verticalArrangement = Arrangement.Top
        ) {
            SettingsHeader()
            val context = LocalContext.current
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
                        .height(80.dp).clickable {
                            context.startActivity(Intent(context, EditProfileActivity::class.java))
                        },
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
                    .height(56.dp)
                    .clickable {
                        context.startActivity(Intent(context, CurrencyActivity::class.java))
                    }) {
                    Text(
                        text = "Currency",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color(0xFF292B2D)
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = if (SharedPrefUtils.getStringData(context,"currency").isNullOrEmpty())
                            "USD"
                        else SharedPrefUtils.getStringData(context,"currency").toString(),
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
                    Text(text = "Log out",Modifier.clickable { scope.launch { stateModal.show() } },
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
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
    Divider(color = Color.LightGray, thickness = 1.dp)
}