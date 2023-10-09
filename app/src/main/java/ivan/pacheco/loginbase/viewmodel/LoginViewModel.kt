package ivan.pacheco.loginbase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ivan.pacheco.loginbase.firebase.FirebaseAuthSingleton
import ivan.pacheco.loginbase.model.UserModel
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

}