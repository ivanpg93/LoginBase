package ivan.pacheco.loginbase.infrastructure.remote

import io.reactivex.Completable

fun interface LogoutWebService {
    fun fetch(): Completable
}