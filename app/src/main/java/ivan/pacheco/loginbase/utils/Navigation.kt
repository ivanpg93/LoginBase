package ivan.pacheco.loginbase.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

sealed class Destination {

    object Back: Destination()
    object Login: Destination()
    object Register: Destination()
    object RecoveryPassword: Destination()
    object CheckEmail: Destination()
    object Home: Destination()

}

interface Navigation {
    val navigationLD: MutableLiveData<Destination>
    fun getNavigationLD(): LiveData<Destination> = navigationLD
}