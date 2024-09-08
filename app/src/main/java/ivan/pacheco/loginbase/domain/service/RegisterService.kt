package ivan.pacheco.loginbase.domain.service

import io.reactivex.Completable

fun interface RegisterService {
    fun register(username: String, password: String): Completable
}