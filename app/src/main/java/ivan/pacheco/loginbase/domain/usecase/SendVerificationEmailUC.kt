package ivan.pacheco.loginbase.domain.usecase

import io.reactivex.Completable
import ivan.pacheco.loginbase.domain.service.SendVerificationEmailService
import javax.inject.Inject

class SendVerificationEmailUC @Inject constructor(private val service: SendVerificationEmailService) {

    fun execute(): Completable = service.sendVerificationEmail()

}