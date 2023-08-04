


import com.koombea.presenter.ui.AuthViewModel
import com.koombea.presenter.ui.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { AuthViewModel(loginUseCase = get()) }
    viewModel { SignUpViewModel(signUpUseCase = get()) }
}
