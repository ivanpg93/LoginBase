package ivan.pacheco.loginbase.domain.usecase

import io.reactivex.Completable
import ivan.pacheco.loginbase.domain.service.LoginCredentialsService
import javax.inject.Inject

class LoginCredentialsUC @Inject constructor(private val service: LoginCredentialsService) {

    fun execute(username: String, password: String): Completable = service.login(username, password)

}