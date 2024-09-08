package ivan.pacheco.loginbase.infrastructure.remote.firebase

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ivan.pacheco.loginbase.utils.Utils.goToLogin
import ivan.pacheco.loginbase.utils.Utils.customToastLong

object FirebaseAuth {

    // Firebase auth singleton instance
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Cierra sesión y te devuelve al login
     */
    fun logout(activity: Activity) {
        auth.signOut()
        goToLogin(activity)
    }

    /**
     * Envía un correo de verificación al registrarse el usuario
     */
    fun sendEmailVerification(user: FirebaseUser?, activity: Activity) {
        user?.sendEmailVerification()?.addOnCompleteListener(activity) {
            if (it.isSuccessful) {
                customToastLong(activity, "Se ha enviado un correo de verificación")
            }
        }
    }

}
