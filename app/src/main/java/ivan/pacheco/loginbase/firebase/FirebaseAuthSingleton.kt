package ivan.pacheco.loginbase.firebase

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ivan.pacheco.loginbase.utils.Utils.goToLogin
import ivan.pacheco.loginbase.utils.Utils.customToastLong
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthSingleton @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    /**
     * Instancia Singleton de Firebase Authentication
     */
    fun getFirebaseAuth(): FirebaseAuth = firebaseAuth

    /**
     * Cierra sesión y te devuelve al login
     */
    fun logout(activity: Activity) {
        firebaseAuth.signOut()
        goToLogin(activity)
    }

    /**
     * Envia un correo de verificación al registrarse el usuario
     */
    fun sendEmailVerification(user: FirebaseUser?, activity: Activity) {
        user?.sendEmailVerification()?.addOnCompleteListener(activity) {
            if (it.isSuccessful) {
                customToastLong(activity, "Se ha enviado un correo de verificación")
            }
        }
    }
}