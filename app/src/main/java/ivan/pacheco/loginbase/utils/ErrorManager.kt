package ivan.pacheco.loginbase.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ivan.pacheco.loginbase.R

sealed class ErrorManager(open val message: Int) {

    companion object {

        /**
         * Map errors to ErrorManager
         */
        fun map(error: Throwable): Int {
            return when (error) {
                is com.google.firebase.FirebaseNetworkException -> FirebaseNetworkException.message
                is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException -> FirebaseAuthInvalidCredentialsException.message
                is com.google.firebase.auth.FirebaseAuthUserCollisionException -> FirebaseAuthUserCollisionException.message
                is com.google.firebase.auth.FirebaseAuthEmailException -> FirebaseAuthEmailException.message
                else -> UnknownError.message
            }
        }
    }

    object FirebaseNetworkException: ErrorManager(R.string.error_connection)
    object FirebaseAuthInvalidCredentialsException: ErrorManager(R.string.error_credentials)
    object FirebaseAuthUserCollisionException: ErrorManager(R.string.error_user_already_exists)
    object FirebaseAuthEmailException: ErrorManager(R.string.recovery_password_error_invalid_email)
    object UnknownError: ErrorManager(R.string.error_unknown)

}

interface Error {
    val errorLD: MutableLiveData<Int>
    fun getErrorLD(): LiveData<Int> = errorLD
}