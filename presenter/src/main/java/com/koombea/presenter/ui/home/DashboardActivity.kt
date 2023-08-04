@file:OptIn(ExperimentalMaterial3Api::class)

package com.koombea.presenter.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.koombea.androidtemplate.ui.theme.AndroidtemplateTheme
import com.koombea.composetest.presenter.ui.home.DashboardScreen
import com.koombea.presenter.ui.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidtemplateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DashboardScreen()
                }
            }
        }
    }
}
