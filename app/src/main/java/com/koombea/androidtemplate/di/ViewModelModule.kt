


import com.koombea.presenter.ui.character.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { CharacterListViewModel(getRootStatusUseCase = get()) }
}
