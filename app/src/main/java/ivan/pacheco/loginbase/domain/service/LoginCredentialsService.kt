package ivan.pacheco.loginbase.domain.service

import io.reactivex.Completable

fun interface LoginCredentialsService {
    fun login(username: String, password: String): Completable
}