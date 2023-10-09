package ivan.pacheco.loginbase.firebase

import com.google.firebase.firestore.FirebaseFirestore
import ivan.pacheco.loginbase.model.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreSingleton @Inject constructor(private val db: FirebaseFirestore, private val auth: FirebaseAuthSingleton){

    /**
     * Instancia Singleton de Firestore
     */
    fun getDb(): FirebaseFirestore = db

    /**
     * Guarda el usuario registrado en la BBDD de Firebase
     */
    fun saveUser(user: UserModel) {
        getDb().collection("users").document(user.username).set(
            hashMapOf("email" to user.username, "password" to user.password, "verificado" to (auth.getFirebaseAuth().currentUser?.isEmailVerified
                ?: false)))
    }

}