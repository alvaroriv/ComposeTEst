


import com.koombea.presenter.ui.login.AuthViewModel
import com.koombea.presenter.ui.login.SettingsViewModel
import com.koombea.presenter.ui.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { AuthViewModel(loginUseCase = get()) }
    viewModel { SignUpViewModel(signUpUseCase = get()) }
    viewModel { SettingsViewModel(getUerUseCase = get()) }
}
