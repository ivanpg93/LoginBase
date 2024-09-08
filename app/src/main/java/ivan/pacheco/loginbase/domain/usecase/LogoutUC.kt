package ivan.pacheco.loginbase.domain.usecase

import io.reactivex.Completable
import ivan.pacheco.loginbase.domain.service.LogoutService
import javax.inject.Inject

class LogoutUC @Inject constructor(private val service: LogoutService) {

    fun execute(): Completable = service.logout()

}