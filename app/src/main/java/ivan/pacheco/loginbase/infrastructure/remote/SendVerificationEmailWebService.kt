package ivan.pacheco.loginbase.infrastructure.remote

import io.reactivex.Completable

fun interface SendVerificationEmailWebService {
    fun fetch(): Completable
}