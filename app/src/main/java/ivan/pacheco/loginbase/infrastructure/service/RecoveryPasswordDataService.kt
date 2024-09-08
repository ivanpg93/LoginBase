package ivan.pacheco.loginbase.infrastructure.service

import io.reactivex.Completable
import ivan.pacheco.loginbase.domain.service.RecoveryPasswordService
import ivan.pacheco.loginbase.infrastructure.remote.RecoveryPasswordWebService
import javax.inject.Inject

class RecoveryPasswordDataService @Inject constructor(private val ws: RecoveryPasswordWebService) :
    RecoveryPasswordService {

    override fun recoveryPassword(username: String): Completable = ws.fetch(username)

}