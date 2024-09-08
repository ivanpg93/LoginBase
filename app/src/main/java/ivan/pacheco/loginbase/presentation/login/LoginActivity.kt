package ivan.pacheco.loginbase.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ivan.pacheco.loginbase.databinding.ActivityLoginBinding
import ivan.pacheco.loginbase.presentation.main.MainActivity
import ivan.pacheco.loginbase.presentation.recoverypassword.RecoveryPasswordActivity
import ivan.pacheco.loginbase.presentation.register.RegisterActivity
import ivan.pacheco.loginbase.utils.Destination
import ivan.pacheco.loginbase.utils.Utils
import ivan.pacheco.loginbase.utils.Utils.hideKeyboard

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val vm: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = "4civanpacheco@gmail.com"
        val password = "123456"

        binding.txtUserName.setText(username)
        binding.txtPassword.setText(password)

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

        binding.btnLogin.setOnClickListener {
            hideKeyboard(it)
            vm.actionLogin(binding.txtUserName.text.toString(), binding.txtPassword.text.toString())
        }

        // Navigate to register
        binding.txtRegister.setOnClickListener { navigate(Destination.Register) }

        // Navigate to recovery password
        binding.txtRecoveryPassword.setOnClickListener { navigate(Destination.RecoveryPassword) }
    }

    private fun navigate(destination: Destination) {
        when(destination) {
            Destination.Home -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            Destination.Register -> startActivity(Intent(this, RegisterActivity::class.java))
            Destination.RecoveryPassword -> startActivity(Intent(this, RecoveryPasswordActivity::class.java))
            else -> { /* Do nothing */ }
        }
    }

}