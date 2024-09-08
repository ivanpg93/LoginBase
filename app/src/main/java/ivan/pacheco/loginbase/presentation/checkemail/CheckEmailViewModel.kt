package ivan.pacheco.loginbase.presentation.checkemail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ivan.pacheco.loginbase.domain.usecase.LogoutUC
import ivan.pacheco.loginbase.domain.usecase.SendVerificationEmailUC
import ivan.pacheco.loginbase.infrastructure.remote.firebase.FirebaseAuth
import ivan.pacheco.loginbase.utils.Destination
import ivan.pacheco.loginbase.utils.Error
import ivan.pacheco.loginbase.utils.ErrorManager
import ivan.pacheco.loginbase.utils.Navigation
import javax.inject.Inject

@HiltViewModel
class CheckEmailViewModel @Inject constructor(
    private val uc: SendVerificationEmailUC,
    private val logoutUC: LogoutUC
) : ViewModel(), Error, Navigation {

    override val errorLD = MutableLiveData<Int>()
    override val navigationLD = MutableLiveData<Destination>()
    private val isLoadingLD = MutableLiveData<Boolean>()
    private val auth = FirebaseAuth.auth
    private val userLD = MutableLiveData<FirebaseUser>()
    private val messageLD = MutableLiveData<String>()

    fun getLoadingLD(): LiveData<Boolean> = isLoadingLD
    fun getUserLD(): LiveData<FirebaseUser> = userLD
    fun getMessageLD(): LiveData<String> = messageLD

    init {

        // Asignamos el valor del currentUser al user
        userLD.value = auth.currentUser
    }

    /**
     * Actualizar el currentUser
     */
    fun reloadUser() {
        userLD.value?.reload()?.addOnCompleteListener { reloadTask ->
            if (reloadTask.isSuccessful) {
                userLD.value = auth.currentUser
            }
        }
    }

    fun actionSendVerificationEmail() {
        uc.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) { isLoadingLD.value = true }

                override fun onComplete() {
                    isLoadingLD.value = false
                    navigationLD.value = Destination.Home
                }

                override fun onError(error: Throwable) {
                    isLoadingLD.value = false
                    errorLD.value = ErrorManager.map(error)
                }

            })
    }

    fun actionLogout() {
        logoutUC.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) { /* Do nothing*/ }
                override fun onComplete() { navigationLD.value = Destination.Login }
                override fun onError(error: Throwable) { /* Do nothing*/ }
            })
    }

}