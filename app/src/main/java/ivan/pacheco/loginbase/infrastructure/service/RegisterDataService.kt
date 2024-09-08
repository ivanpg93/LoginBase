package ivan.pacheco.loginbase.infrastructure.service

import io.reactivex.Completable
import ivan.pacheco.loginbase.domain.service.RegisterService
import ivan.pacheco.loginbase.infrastructure.remote.RegisterWebService
import javax.inject.Inject

class RegisterDataService @Inject constructor(private val ws: RegisterWebService) :
    RegisterService {

    override fun register(username: String, password: String): Completable =
        ws.fetch(username, password)

}