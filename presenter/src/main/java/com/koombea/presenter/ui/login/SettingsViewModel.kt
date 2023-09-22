package com.koombea.presenter.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.base.model.User
import com.koombea.domain.usecase.EditProfileUseCase
import com.koombea.domain.usecase.GetUserUseCase
import com.koombea.domain.usecase.SignOutUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(private val getUserUseCase: GetUserUseCase,
                        private val signOutUseCase: SignOutUseCase,
                        private val editProfileUseCase: EditProfileUseCase) : ViewModel() {

    private val _state = MutableStateFlow(DashboardActivityState())

    val state: StateFlow<DashboardActivityState>
        get() = _state

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserUseCase.perform().let { result ->
                when (result) {
                    is OperationResult.Success -> {
//                        _state.value = _state.value.copy(user = result.data)
                        _state.update {
                            it.copy(user = result.data)
                        }
                    }
                    is OperationResult.Error -> {
                        _state.value = _state.value.copy(message = result.exception?.message )
                    }

                }
            }
        }
    }

    fun signOut(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            signOutUseCase.perform(user).let { result ->
                when (result) {
                    is OperationResult.Success -> {
                        _state.value = _state.value.copy(user = null, message = "Logout")
                    }
                    is OperationResult.Error -> {
                        _state.value = _state.value.copy(message = result.exception?.message )
                    }

                }
            }
        }
    }

    fun editUser(user: User?) {
        viewModelScope.launch(Dispatchers.IO) {
            editProfileUseCase.perform(user).let { result ->
                when (result) {
                    is OperationResult.Success -> {
                        _state.value = _state.value.copy(message = "User updated")
                    }
                    is OperationResult.Error -> {
                        _state.value = _state.value.copy(message = result.exception?.message )
                    }

                }
            }
        }
    }
}

data class DashboardActivityState(
    val message: String? = "",
    val user: User? = null
)