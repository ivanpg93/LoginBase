package ivan.pacheco.loginbase.infrastructure.remote.webservice

import io.reactivex.Completable
import ivan.pacheco.loginbase.infrastructure.remote.RecoveryPasswordWebService
import ivan.pacheco.loginbase.infrastructure.remote.firebase.FirebaseAuth

class RecoveryPasswordWS: RecoveryPasswordWebService {

    override fun fetch(username: String): Completable {
        return Completable.create { emitter ->
            FirebaseAuth.auth
                .sendPasswordResetEmail(username)
                .addOnSuccessListener { emitter.onComplete() }
                .addOnFailureListener { error -> emitter.onError(error) }
        }
    }

}