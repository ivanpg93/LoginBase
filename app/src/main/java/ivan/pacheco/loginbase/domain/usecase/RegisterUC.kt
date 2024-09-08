package ivan.pacheco.loginbase.domain.usecase

import io.reactivex.Completable
import ivan.pacheco.loginbase.domain.service.RegisterService
import javax.inject.Inject

class RegisterUC @Inject constructor(private val service: RegisterService) {

    fun execute(username: String, password: String): Completable = service.register(username, password)

}