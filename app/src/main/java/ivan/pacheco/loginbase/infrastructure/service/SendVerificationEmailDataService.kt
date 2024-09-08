package ivan.pacheco.loginbase.infrastructure.service

import io.reactivex.Completable
import ivan.pacheco.loginbase.domain.service.SendVerificationEmailService
import ivan.pacheco.loginbase.infrastructure.remote.SendVerificationEmailWebService
import javax.inject.Inject

class SendVerificationEmailDataService @Inject constructor(private val ws: SendVerificationEmailWebService): SendVerificationEmailService {

    override fun sendVerificationEmail(): Completable = ws.fetch()

}