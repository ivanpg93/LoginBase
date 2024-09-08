package ivan.pacheco.loginbase.infrastructure.remote

import io.reactivex.Completable

fun interface LoginCredentialsWebService {
    fun fetch(username: String, password: String): Completable
}