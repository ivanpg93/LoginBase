package ivan.pacheco.loginbase.view

import android.os.Bundle
import android.util.Patterns
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ivan.pacheco.loginbase.databinding.ActivityRecoveryPasswordBinding
import ivan.pacheco.loginbase.utils.Utils.customAlertError
import ivan.pacheco.loginbase.utils.Utils.goToLogin
import ivan.pacheco.loginbase.viewmodel.RecoveryPasswordViewModel

@AndroidEntryPoint
class RecoveryPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecoveryPasswordBinding

    private val recoveryPasswordViewModel: RecoveryPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoveryPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRecovery.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                recoveryPasswordViewModel.auth.fetchSignInMethodsForEmail(email)
                    .addOnCompleteListener {
                        recoveryPasswordViewModel.auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener { passwordResetTask ->
                                if (passwordResetTask.isSuccessful) {
                                    goToLogin(this)
                                } else {
                                    customAlertError(this, "Error al enviar el correo electrónico.")
                                }
                            }
                    }
            } else {
                customAlertError(this, "Introduce un email válido.")
            }
        }
    }

}