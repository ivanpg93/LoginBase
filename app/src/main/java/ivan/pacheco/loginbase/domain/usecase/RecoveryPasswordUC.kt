package ivan.pacheco.loginbase.domain.usecase

import io.reactivex.Completable
import ivan.pacheco.loginbase.domain.service.RecoveryPasswordService
import javax.inject.Inject

class RecoveryPasswordUC @Inject constructor(private val service: RecoveryPasswordService) {

    fun execute(username: String): Completable = service.recoveryPassword(username)

}