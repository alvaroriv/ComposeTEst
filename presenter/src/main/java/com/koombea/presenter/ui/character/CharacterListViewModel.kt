package com.koombea.presenter.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koombea.data.character.base.OperationResult
import com.koombea.domain.usecase.GetRootStatusUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel(private val getRootStatusUseCase: GetRootStatusUseCase) : ViewModel() {

    private val _state = MutableStateFlow(MainActivityState())
    val state: StateFlow<MainActivityState>
        get() = _state

    fun getRootStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            getRootStatusUseCase.perform().let { result ->
                when (result) {
                    is OperationResult.Success ->
                        _state.value = _state.value.copy(rootMessage = result.data)
                    is OperationResult.Error -> onGetCharacterListError(result)
                }
            }
        }
    }

    private fun onGetCharacterListError(result: OperationResult.Error) {
        _state.value = _state.value.copy(loading = false, list = emptyList(),
        error = result.exception?.message )
    }
}

data class MainActivityState(
    val loading: Boolean? = false,
    val list: List<String>? = listOf(),
    val error: String? = "",
    val rootMessage: Boolean? = false
)

