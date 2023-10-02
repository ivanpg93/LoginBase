package ivan.pacheco.loginbase.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ivan.pacheco.loginbase.firebase.FirebaseAuthSingleton
import ivan.pacheco.loginbase.model.UserModel
import ivan.pacheco.loginbase.view.MainActivity
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val firebaseAuth: FirebaseAuthSingleton) : ViewModel() {

    val auth = firebaseAuth.getFirebaseAuth()
    val userModelLiveData = MutableLiveData<UserModel>()

    /**
     * Actualizamos los valores del LiveData
     */
    fun login(username: String, password: String) {
        val userModel = UserModel(username, password)
        userModelLiveData.value = userModel
    }

    /**
     * Enviamos username y password a la pantalla Main
     */
    fun sendVariablesToMain(context: Context, username: String?, password: String?) {
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra("userName", username)
            putExtra("password", password)
        }
        context.startActivity(intent)
    }

}