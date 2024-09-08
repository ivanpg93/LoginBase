package ivan.pacheco.loginbase.infrastructure.remote

import io.reactivex.Completable

interface RecoveryPasswordWebService {
    fun fetch(username: String) : Completable
}