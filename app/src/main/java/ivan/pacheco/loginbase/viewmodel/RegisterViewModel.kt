package ivan.pacheco.loginbase.viewmodel

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ivan.pacheco.loginbase.firebase.FirebaseAuthSingleton
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val firebaseAuth: FirebaseAuthSingleton) : ViewModel() {

    val auth = firebaseAuth.getFirebaseAuth()

    // Variables para validar el email
    var messageEmailValid = MutableLiveData<String>()
    var isEmailValid = MutableLiveData<Boolean>()

    // Variables para validar la password
    val messagePasswordValid = MutableLiveData<String>()
    var isPasswordValid = MutableLiveData<Boolean>()

    // Variables para validar la confirmación de la password
    val messagePasswordEquals = MutableLiveData<String>()
    var arePasswordEquals = MutableLiveData<Boolean>()

    /**
     * Validamos el email y enviamos un mensaje al usuario
     */
    fun validateEmail(email: String): Boolean {
        val message: String
        if (email.isNullOrBlank()) {
            message = "El correo electrónico no puede estar vacío"
            isEmailValid.value = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            message = "Escribe un correo electrónico válido"
            isEmailValid.value = false
        } else {
            message = null.toString()
            isEmailValid.value = true
        }
        messageEmailValid.value = message
        return isEmailValid.value!!
    }

    /**
     * Validamos la password y enviamos un mensaje al usuario
     */
    fun validatePassword(password: String): Boolean {
        val message: String
        if (password.length < 6) {
            message = "La contraseña debe tener mínimo 6 caracteres"
            isPasswordValid.value = false
        } else {
            message = null.toString()
            isPasswordValid.value = true
        }
        messagePasswordValid.value = message
        return isPasswordValid.value!!
    }

    /**
     * Validamos que sean la misma password y enviamos un mensaje al usuario
     */
    fun validatePasswordEquals(password1: String, password2: String): Boolean {
        val message: String
        if (password1 != password2) {
            message = "Las contraseñas deben coincidir"
            arePasswordEquals.value = false
        } else {
            message = null.toString()
            arePasswordEquals.value = true
        }
        messagePasswordEquals.value = message
        return arePasswordEquals.value!!
    }

    /**
     * Validamos el email y password y enviamos un mensaje al usuario
     */
    fun validateCredentials(email: String, password: String, password2: String): Boolean {
        return validateEmail(email) && validatePassword(password) && validatePasswordEquals(password, password2)
    }

}