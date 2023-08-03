package com.koombea.presenter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koombea.data.character.base.model.User
import com.koombea.data.character.base.OperationResult
import com.koombea.domain.usecase.LoginUseCase
import com.koombea.domain.usecase.SignUpUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _state = MutableStateFlow(MainActivityState())

    val state: StateFlow<MainActivityState>
        get() = _state

    fun login(email: String, password: String) {
        _state.value = _state.value.copy(isLogged = -1)
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.perform(email, password).let { result ->
                when (result) {
                    is OperationResult.Success -> {
                        _state.value = _state.value.copy(isLogged = 1)
                    }
                    is OperationResult.Error -> {
                        _state.value = _state.value.copy(isLogged = 0)
                    }

                }
            }
        }
    }

    fun signup(user: User) {
        _state.value = _state.value.copy(isLogged = -1)
        viewModelScope.launch(Dispatchers.IO) {
//            signUpUseCase.perform(user).let { result ->
//                when (result) {
//                    is OperationResult.Success -> {
//                        _state.value = _state.value.copy(isLogged = 1)
//                    }
//                    is OperationResult.Error -> {
//                        _state.value = _state.value.copy(isLogged = 0)
//                    }
//
//                }
//            }
        }
    }

    fun updateUserPassword(password: String) {
        _state.update { it.copy(userName = password) }
    }

    fun updateUserEmail(email: String) {
        _state.update { it.copy(userEmail = email) }
    }


    private fun onGetCharacterListError(result: OperationResult.Error) {
        _state.value = _state.value.copy(error = result.exception?.message )
    }
}

data class MainActivityState(
    val error: String? = "",
    val isLogged: Int = -1,
    val userName: String = "",
    val userEmail: String = ""
)