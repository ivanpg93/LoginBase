package ivan.pacheco.loginbase.infrastructure.remote

import io.reactivex.Completable

fun interface RegisterWebService {
    fun fetch (username: String, password: String): Completable
}