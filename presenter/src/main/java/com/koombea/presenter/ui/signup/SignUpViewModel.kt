package com.koombea.presenter.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koombea.data.character.base.model.User
import com.koombea.data.character.base.OperationResult
import com.koombea.domain.usecase.SignUpUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _state = MutableStateFlow(SignUpActivityState())

    val state: StateFlow<SignUpActivityState>
        get() = _state

    fun signup(user: User) {
        _state.value = _state.value.copy(isSignup = -1)
        viewModelScope.launch(Dispatchers.IO) {
            signUpUseCase.perform(user).let { result ->
                when (result) {
                    is OperationResult.Success -> {
                        _state.value = _state.value.copy(isSignup = 1)
                    }
                    is OperationResult.Error -> {
                        _state.value = _state.value.copy(isSignup = 0)
                    }

                }
            }
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

data class SignUpActivityState(
    val error: String? = "",
    val isSignup: Int = -1,
    val userName: String = "",
    val userEmail: String = ""
)