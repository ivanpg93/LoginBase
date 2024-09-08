package ivan.pacheco.loginbase.presentation.recoverypassword

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ivan.pacheco.loginbase.R
import ivan.pacheco.loginbase.databinding.ActivityRecoveryPasswordBinding
import ivan.pacheco.loginbase.presentation.main.MainActivity
import ivan.pacheco.loginbase.utils.Destination
import ivan.pacheco.loginbase.utils.Utils
import ivan.pacheco.loginbase.utils.Utils.customAlertError
import ivan.pacheco.loginbase.utils.Utils.hideKeyboard

@AndroidEntryPoint
class RecoveryPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecoveryPasswordBinding

    private val vm: RecoveryPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoveryPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Loading
        vm.getLoadingLD().observe(this) { isLoading ->
            if (isLoading) {
                binding.loadingView.visibility = View.VISIBLE
            } else {
                binding.loadingView.visibility = View.GONE
            }
        }

        // Error
        vm.getErrorLD().observe(this) { error -> Utils.customAlertWarning(this, getString(error)) }

        // Navigation
        vm.getNavigationLD().observe(this, ::navigate)

        binding.btnRecovery.setOnClickListener {
            hideKeyboard(it)
            val email = binding.txtEmail.text.toString()
            if (email.isNotEmpty()) {
                vm.actionRecoveryPassword(email)
            } else {
                customAlertError(this, getString(R.string.recovery_password_error_invalid_email))
            }
        }
    }

    private fun navigate(destination: Destination) {
        when(destination) {
            Destination.Login -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else -> { /* Do nothing */ }
        }
    }

}