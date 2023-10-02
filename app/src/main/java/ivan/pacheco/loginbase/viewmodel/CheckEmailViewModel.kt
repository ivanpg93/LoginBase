package ivan.pacheco.loginbase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import ivan.pacheco.loginbase.firebase.FirebaseAuthSingleton
import javax.inject.Inject

@HiltViewModel
class CheckEmailViewModel @Inject constructor(val firebaseAuth: FirebaseAuthSingleton) : ViewModel() {

    val auth = firebaseAuth.getFirebaseAuth()
    val user = MutableLiveData<FirebaseUser>()

    fun onCreate() {
        // Asignamos el valor del currentUser al user
        user.value = auth.currentUser
    }

    /**
     * Actualizar el currentUser
     */
    fun reloadUser() {
        user.value?.reload()?.addOnCompleteListener { reloadTask ->
            if (reloadTask.isSuccessful) {
                user.value = auth.currentUser
            }
        }
    }

}