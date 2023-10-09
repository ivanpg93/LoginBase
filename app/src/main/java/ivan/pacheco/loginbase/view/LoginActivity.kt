package ivan.pacheco.loginbase.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ivan.pacheco.loginbase.databinding.ActivityLoginBinding
import ivan.pacheco.loginbase.utils.Utils
import ivan.pacheco.loginbase.utils.Utils.customAlertError
import ivan.pacheco.loginbase.utils.Utils.goToMain
import ivan.pacheco.loginbase.utils.Utils.goToRecoveryPassword
import ivan.pacheco.loginbase.utils.Utils.goToRegister
import ivan.pacheco.loginbase.viewmodel.LoginViewModel

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginUser()

        binding.txtRegister.setOnClickListener {
            goToRegister(this)
        }

        binding.txtRecoveryPassword.setOnClickListener {
            goToRecoveryPassword(this)
        }
    }

    /**
     * Hacemos login con el usuario introducido
     */
    private fun loginUser() {
        val errorMessage = "El usuario o la contraseña no son válidos."

        binding.btnLogin.setOnClickListener {
            Utils.hideKeyboard(it, this)
            val username = binding.txtUserName.text.toString()
            val password = binding.txtPassword.text.toString()

            if (username.isNotBlank() && password.isNotBlank()) {
                loginViewModel.login(username, password)
                loginViewModel.auth.signInWithEmailAndPassword(
                    username, password
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        goToMain(this)
                    } else {
                        customAlertError(this, errorMessage)
                    }
                }
            } else {
                customAlertError(this, errorMessage)
            }
        }
    }

}