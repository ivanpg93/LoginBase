package ivan.pacheco.loginbase.presentation.checkemail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ivan.pacheco.loginbase.R
import ivan.pacheco.loginbase.databinding.ActivityCheckEmailBinding
import ivan.pacheco.loginbase.presentation.login.LoginActivity
import ivan.pacheco.loginbase.presentation.main.MainActivity
import ivan.pacheco.loginbase.utils.Destination
import ivan.pacheco.loginbase.utils.Utils.customAlertWarning

@AndroidEntryPoint
class CheckEmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckEmailBinding
    private val vm: CheckEmailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Reload current user
        vm.reloadUser()

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

        vm.getUserLD().observe(this) { user ->
            if (user != null) {
                if (user.isEmailVerified) {
                    Destination.Home
                } else {
                    vm.actionSendVerificationEmail()
                    customAlertWarning(this, getString(R.string.check_email_verification_email_sent))
                }
            } else {
                navigate(Destination.Login)
            }

            // Navigate to Home
            binding.btnContinue.setOnClickListener {
                vm.reloadUser()
                if (user.isEmailVerified) {
                    navigate(Destination.Home)
                } else {
                    customAlertWarning(this, getString(R.string.check_email_verify_your_account))
                }
            }
        }

        // Navigate to Login
        binding.btnExit.setOnClickListener { vm.actionLogout() }

    }

    private fun navigate(destination: Destination) {
        when (destination) {
            Destination.Home -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            Destination.Login -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            else -> { /* Do nothing */ }
        }
    }

}