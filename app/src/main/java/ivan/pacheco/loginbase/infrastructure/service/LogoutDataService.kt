package ivan.pacheco.loginbase.infrastructure.service

import io.reactivex.Completable
import ivan.pacheco.loginbase.domain.service.LogoutService
import ivan.pacheco.loginbase.infrastructure.remote.LogoutWebService
import javax.inject.Inject

class LogoutDataService @Inject constructor(private val ws: LogoutWebService): LogoutService {

    override fun logout(): Completable = ws.fetch()

}