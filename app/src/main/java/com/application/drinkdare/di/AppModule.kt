import com.application.drinkdare.data.remote.AuthService
import com.application.drinkdare.data.remote.CartaService
import com.application.drinkdare.data.remote.RegisterService
import com.application.drinkdare.data.repositoy.AuthRepository
import com.application.drinkdare.data.repositoy.CartaRepository
import com.application.drinkdare.data.repositoy.RegisterRepository
import com.application.drinkdare.domain.usecase.CartaUseCase
import com.application.drinkdare.presentation.CartaViewModel
import com.application.drinkdare.presentation.LoginViewModel
import com.application.drinkdare.presentation.RegisterViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    // Firestore
    single { FirebaseFirestore.getInstance() }

    // Servicio Remoto (API o Firestore)
    single { CartaService(get()) }

    // Repositorio (conecta servicio con la capa de dominio)
    single { CartaRepository(get()) }

    // Caso de uso (lógica de negocio)
    single { CartaUseCase(get()) }

    // ViewModel
    viewModel { CartaViewModel(get()) }



    single { AuthService(get()) }

    single { AuthRepository() }

    single { RegisterService() }

    single { RegisterRepository(get()) }

    viewModel { RegisterViewModel() }

    viewModel { LoginViewModel() }
}
