package ivan.pacheco.loginbase.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ivan.pacheco.loginbase.firebase.FirebaseAuthSingleton
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val firebaseAuth: FirebaseAuthSingleton) : ViewModel() {
    val auth = firebaseAuth.getFirebaseAuth()
}