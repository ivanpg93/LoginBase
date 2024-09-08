package ivan.pacheco.loginbase.domain.service

import io.reactivex.Completable

fun interface LogoutService {
    fun logout(): Completable
}