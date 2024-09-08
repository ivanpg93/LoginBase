package ivan.pacheco.loginbase.infrastructure.remote.webservice

import io.reactivex.Completable
import ivan.pacheco.loginbase.infrastructure.remote.RegisterWebService
import ivan.pacheco.loginbase.infrastructure.remote.firebase.FirebaseAuth
import ivan.pacheco.loginbase.infrastructure.remote.firebase.Firestore

class RegisterWS : RegisterWebService {

    companion object {
        private const val USERS = "users"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }

    override fun fetch(username: String, password: String): Completable {
        return Completable.create { emitter ->

            // Create user in Firebase Authentication with credentials
            FirebaseAuth.auth
                .createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener { register ->
                    if (register.isSuccessful) {

                        // Get user collection from Firestore DB
                        val users = Firestore.db.collection(USERS)

                        // User data
                        val data = hashMapOf(
                            EMAIL to username,
                            PASSWORD to password,
                        )

                        // Stored user data to Firestore DB
                        users.document(username).set(data)
                            .addOnSuccessListener {
                                register.result?.user?.let { emitter.onComplete() }
                            }
                            .addOnFailureListener { error -> emitter.onError(error) }
                    } else {
                        val error = register.exception ?: Exception()
                        emitter.onError(error)
                    }
                }
        }
    }

}