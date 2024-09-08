package ivan.pacheco.loginbase.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ivan.pacheco.loginbase.domain.usecase.RegisterUC
import ivan.pacheco.loginbase.utils.Destination
import ivan.pacheco.loginbase.utils.Error
import ivan.pacheco.loginbase.utils.ErrorManager
import ivan.pacheco.loginbase.utils.Navigation
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val uc: RegisterUC) : ViewModel(), Error, Navigation {

    override val errorLD = MutableLiveData<Int>()
    override val navigationLD = MutableLiveData<Destination>()
    private val isLoadingLD = MutableLiveData<Boolean>()
    fun getLoadingLD(): LiveData<Boolean> = isLoadingLD

    fun actionRegister(username: String, password: String) {
        uc.execute(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) { isLoadingLD.value = true }

                override fun onComplete() {
                    isLoadingLD.value = false
                    navigationLD.value = Destination.CheckEmail
                }

                override fun onError(error: Throwable) {
                    isLoadingLD.value = false
                    errorLD.value = ErrorManager.map(error)
                }
            })
    }

}