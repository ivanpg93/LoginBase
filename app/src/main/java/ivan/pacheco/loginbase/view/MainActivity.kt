package ivan.pacheco.loginbase.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ivan.pacheco.loginbase.R
import ivan.pacheco.loginbase.databinding.ActivityMainBinding
import ivan.pacheco.loginbase.utils.Utils.goToCheckEmail
import ivan.pacheco.loginbase.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = mainViewModel.auth.currentUser

        if (user == null) {
            mainViewModel.firebaseAuth.logout(this)
        } else {
            if(!user.isEmailVerified) {
                mainViewModel.firebaseAuth.sendEmailVerification(user, this)
                goToCheckEmail(this)
            }
        }

        binding.menuNavigation.menu.findItem(R.id.menuLogout).setOnMenuItemClickListener {
            mainViewModel.firebaseAuth.logout(this)
            true
        }
    }

}