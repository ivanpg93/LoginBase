package ivan.pacheco.loginbase.infrastructure.remote.webservice

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ivan.pacheco.loginbase.infrastructure.remote.LoginCredentialsWebService
import ivan.pacheco.loginbase.infrastructure.remote.firebase.FirebaseAuth

class LoginCredentialsWS : LoginCredentialsWebService {
    override fun fetch(username: String, password: String): Completable {
        return Completable.create { emitter ->
            FirebaseAuth.auth
                .signInWithEmailAndPassword(username, password)
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { error -> emitter.onError(error) }
        }
    }
}