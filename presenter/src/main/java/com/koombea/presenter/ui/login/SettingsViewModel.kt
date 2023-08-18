package com.koombea.presenter.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.base.model.User
import com.koombea.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(private val getUerUseCase: GetUserUseCase) : ViewModel() {

    private val _state = MutableStateFlow(DashboardActivityState())

    val state: StateFlow<DashboardActivityState>
        get() = _state

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            getUerUseCase.perform().let { result ->
                when (result) {
                    is OperationResult.Success -> {
                        _state.value = _state.value.copy(user = result.data)
                    }
                    is OperationResult.Error -> {
                        _state.value = _state.value.copy(error = result.exception?.message )
                    }

                }
            }
        }
    }
}

data class DashboardActivityState(
    val error: String? = "",
    val user: User? = null
)