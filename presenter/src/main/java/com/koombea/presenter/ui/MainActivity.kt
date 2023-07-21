@file:OptIn(ExperimentalMaterial3Api::class)

package com.koombea.presenter.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import com.koombea.presenter.ui.splash.SplashViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidtemplateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(authViewModel)
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
                            Toast.makeText(this@MainActivity,"Success", Toast.LENGTH_LONG).show()
                        }

                        0 -> {
                            Toast.makeText(this@MainActivity,"Error", Toast.LENGTH_LONG).show()
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
