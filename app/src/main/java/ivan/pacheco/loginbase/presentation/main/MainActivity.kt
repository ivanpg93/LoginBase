package ivan.pacheco.loginbase.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ivan.pacheco.loginbase.R
import ivan.pacheco.loginbase.databinding.ActivityMainBinding
import ivan.pacheco.loginbase.infrastructure.remote.firebase.FirebaseAuth
import ivan.pacheco.loginbase.utils.Utils.goToCheckEmail

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = vm.auth.currentUser

        if (user == null) {
            FirebaseAuth.logout(this)
        } else {
            if(!user.isEmailVerified) {
                FirebaseAuth.sendEmailVerification(user, this)
                goToCheckEmail(this)
            }
        }

        binding.menuNavigation.menu.findItem(R.id.menuLogout).setOnMenuItemClickListener {
            FirebaseAuth.logout(this)
            true
        }
    }

}