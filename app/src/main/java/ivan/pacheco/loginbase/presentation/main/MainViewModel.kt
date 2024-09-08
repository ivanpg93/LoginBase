package ivan.pacheco.loginbase.presentation.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ivan.pacheco.loginbase.infrastructure.remote.firebase.FirebaseAuth
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val auth = FirebaseAuth.auth
}