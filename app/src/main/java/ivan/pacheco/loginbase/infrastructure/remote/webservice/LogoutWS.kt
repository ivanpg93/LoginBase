package ivan.pacheco.loginbase.infrastructure.remote.webservice

import io.reactivex.Completable
import ivan.pacheco.loginbase.infrastructure.remote.LogoutWebService
import ivan.pacheco.loginbase.infrastructure.remote.firebase.FirebaseAuth

class LogoutWS: LogoutWebService {

    override fun fetch(): Completable = Completable.create { FirebaseAuth.auth.signOut() }

}