package ivan.pacheco.loginbase.infrastructure.service

import io.reactivex.Completable
import ivan.pacheco.loginbase.domain.service.LoginCredentialsService
import ivan.pacheco.loginbase.infrastructure.remote.LoginCredentialsWebService
import javax.inject.Inject

class LoginCredentialsDataService @Inject constructor(private val ws: LoginCredentialsWebService):
    LoginCredentialsService {

    override fun login(username: String, password: String): Completable = ws.fetch(username, password)

}