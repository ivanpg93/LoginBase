package ivan.pacheco.loginbase.domain.service

import io.reactivex.Completable

fun interface RecoveryPasswordService {
    fun recoveryPassword(username: String): Completable
}