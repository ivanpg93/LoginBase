package ivan.pacheco.loginbase.presentation.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ivan.pacheco.loginbase.R
import ivan.pacheco.loginbase.databinding.ActivityRegisterBinding
import ivan.pacheco.loginbase.presentation.checkemail.CheckEmailActivity
import ivan.pacheco.loginbase.utils.Destination
import ivan.pacheco.loginbase.utils.Utils.customAlertWarning
import ivan.pacheco.loginbase.utils.Utils.hideKeyboard

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val vm: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
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
        vm.getErrorLD().observe(this) { error -> customAlertWarning(this, getString(error)) }

        // Navigation
        vm.getNavigationLD().observe(this, ::navigate)

        // Register action
        binding.btnRegister.setOnClickListener {
            hideKeyboard(it)
            if (isValidForm()) {
                vm.actionRegister(binding.txtUserName.text.toString(), binding.txtPassword.text.toString())
            }
        }
    }

    private fun isValidForm(): Boolean {
        var validForm = true

        // Check email
        if(!isValidEmail(binding.txtUserName.text.toString())) {
            binding.txtUserName.error = getString(R.string.register_error_invalid_email)
            validForm = false
        } else {
            binding.txtUserName.error = null
        }

        // Check password length and if they match
        if(!checkValidPasswordLength(binding.txtPassword.text.toString())) {
            binding.txtPassword.error = getString(R.string.register_error_password_length)
            validForm = false
        } else {
            binding.txtPassword.error = null
        }

        if (!checkValidPasswords()) {
            binding.txtPassword2.error = resources.getText(R.string.register_error_equal_password)
            validForm = false
        } else {
            binding.txtPassword2.error = null
        }

        return validForm
    }

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun checkValidPasswordLength(password: String): Boolean { return password.length > 5 }

    private fun checkValidPasswords(): Boolean {
        val password = binding.txtPassword.text.toString()
        val password2 = binding.txtPassword2.text.toString()
        return password == password2
    }

    private fun navigate(destination: Destination) {
        when (destination) {
            Destination.CheckEmail -> {
                startActivity(Intent(this, CheckEmailActivity::class.java))
                finish()
            }
            else -> { /* Do nothing */ }
        }
    }

}