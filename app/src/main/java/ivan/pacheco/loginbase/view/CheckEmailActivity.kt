package ivan.pacheco.loginbase.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ivan.pacheco.loginbase.databinding.ActivityCheckEmailBinding
import ivan.pacheco.loginbase.utils.Utils.customAlertWarning
import ivan.pacheco.loginbase.utils.Utils.goToMain
import ivan.pacheco.loginbase.viewmodel.CheckEmailViewModel

@AndroidEntryPoint
class CheckEmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckEmailBinding

    private val checkEmailViewModel: CheckEmailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkEmailViewModel.onCreate()

        checkEmailViewModel.user.observe(this) { user ->
            if (user != null && user.isEmailVerified) {
                goToMain(this)
            } else {
                customAlertWarning(this,
                    "Debes verificar la cuenta con el correo electrónico que se te ha enviado anteriormente antes de continuar."
                )
            }
        }

        binding.btnContinue.setOnClickListener {
            checkEmailViewModel.reloadUser()
        }

        binding.btnExit.setOnClickListener {
            checkEmailViewModel.firebaseAuth.logout(this)
        }

    }

}