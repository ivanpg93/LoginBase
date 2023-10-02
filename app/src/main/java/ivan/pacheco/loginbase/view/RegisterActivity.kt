package ivan.pacheco.loginbase.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ivan.pacheco.loginbase.databinding.ActivityRegisterBinding
import ivan.pacheco.loginbase.firebase.FirebaseAuthSingleton
import ivan.pacheco.loginbase.utils.Utils
import ivan.pacheco.loginbase.utils.Utils.customAlertError
import ivan.pacheco.loginbase.utils.Utils.goToCheckEmail
import ivan.pacheco.loginbase.viewmodel.RegisterViewModel
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerUser()

        /**
         * Listener del username
         */
        binding.txtUserName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!registerViewModel.validateEmail(binding.txtUserName.text.toString())) {
                    binding.txtUserName.setError(registerViewModel.messageEmailValid.value, null)
                } else {
                    binding.txtUserName.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        /**
         * Listener del password
         */
        binding.txtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!registerViewModel.validatePassword(binding.txtPassword.text.toString())) {
                    binding.txtPassword.setError(registerViewModel.messagePasswordValid.value, null)
                } else {
                    binding.txtPassword.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        /**
         * Listener del password2
         */
        binding.txtPassword2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!registerViewModel.validatePasswordEquals(binding.txtPassword.text.toString(), binding.txtPassword2.text.toString())) {
                    binding.txtPassword2.setError(registerViewModel.messagePasswordEquals.value, null)
                } else {
                    binding.txtPassword2.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    /**
     * Registrar el usuario
     */
    private fun registerUser() {
        binding.btnRegister.setOnClickListener {
            Utils.hideKeyboard(it, this)
            if (registerViewModel.validateCredentials(
                    binding.txtUserName.text.toString(),
                    binding.txtPassword.text.toString(),
                    binding.txtPassword2.text.toString()
                )
            ) {
                // Registramos el usuario
                registerViewModel.auth.createUserWithEmailAndPassword(
                    binding.txtUserName.text.toString(),
                    binding.txtPassword.text.toString()
                ).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        registerViewModel.firebaseAuth.sendEmailVerification(registerViewModel.auth.currentUser, this)
                        goToCheckEmail(this)
                    }
                }
            } else {
                customAlertError(this, "El usuario o la contraseña no son válidos.")
            }
        }
    }

}