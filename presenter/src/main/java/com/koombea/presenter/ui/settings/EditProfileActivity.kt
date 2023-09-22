package com.koombea.presenter.ui.settings

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.koombea.androidtemplate.ui.theme.AndroidtemplateTheme
import com.koombea.presenter.ui.home.DashboardActivity
import com.koombea.presenter.ui.login.SettingsViewModel
import com.koombea.presenter.ui.signup.SignUpScreen
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfileActivity : ComponentActivity() {

    private val settingsViewModel: SettingsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsViewModel.getUser()

        setContent {
            AndroidtemplateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EditProfileScreen(settingsViewModel)
                }
            }
        }
        observeViewModel()
    }

    private fun observeViewModel(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                settingsViewModel.state.collect {
                    if(it.message?.isNotEmpty() == true)
                    Toast.makeText(this@EditProfileActivity,it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
