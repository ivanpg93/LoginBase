package ivan.pacheco.loginbase.infrastructure.remote.webservice

import io.reactivex.Completable
import ivan.pacheco.loginbase.infrastructure.remote.SendVerificationEmailWebService
import ivan.pacheco.loginbase.infrastructure.remote.firebase.FirebaseAuth

class SendVerificationEmailWS : SendVerificationEmailWebService {

    override fun fetch(): Completable {
        return Completable.create { emitter ->
            FirebaseAuth.auth.currentUser?.let { user ->
                user.sendEmailVerification()
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { error -> emitter.onError(error) }
            }
        }
    }

}