package ivan.pacheco.loginbase.domain.service

import io.reactivex.Completable

fun interface SendVerificationEmailService {
    fun sendVerificationEmail(): Completable
}