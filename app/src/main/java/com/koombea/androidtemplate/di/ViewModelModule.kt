


import com.koombea.presenter.ui.login.AuthViewModel
import com.koombea.presenter.ui.login.SettingsViewModel
import com.koombea.presenter.ui.login.TransactionViewModel
import com.koombea.presenter.ui.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { AuthViewModel(loginUseCase = get(), getUserUseCase = get()) }
    viewModel { SignUpViewModel(signUpUseCase = get()) }
    viewModel { SettingsViewModel(getUerUseCase = get(), signOutUseCase = get()) }
    viewModel { TransactionViewModel(getTransactionsUseCase = get(), addTransactionUseCase = get()) }
}
