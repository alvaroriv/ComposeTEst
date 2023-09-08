@file:OptIn(ExperimentalMaterial3Api::class)

package com.koombea.presenter.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.koombea.androidtemplate.ui.theme.AndroidtemplateTheme
import com.koombea.presenter.ui.home.DashboardActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidtemplateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(authViewModel)
                }
            }
        }
        observeViewModel()
    }

    private fun observeViewModel(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel.state.collect {
                    when (it.isLogged) {
                        1 -> {
                            startActivity(Intent(this@MainActivity, DashboardActivity::class.java))
                            finish()
                        }

                        0 -> {
                            startActivity(Intent(this@MainActivity, DashboardActivity::class.java))
                            finish()
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    AndroidtemplateTheme {
      //  MainScreen()
    }
}
