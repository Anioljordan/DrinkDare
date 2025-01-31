import com.application.drinkdare.data.remote.CartaService
import com.application.drinkdare.data.repositoy.CartaRepository
import com.application.drinkdare.domain.usecase.CartaUseCase
import com.application.drinkdare.presentation.CartaViewModel
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
}
