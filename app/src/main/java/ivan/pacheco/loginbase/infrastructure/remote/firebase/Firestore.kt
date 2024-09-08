package ivan.pacheco.loginbase.infrastructure.remote.firebase

import com.google.firebase.firestore.FirebaseFirestore

object Firestore {

    // Firebase firestore singleton instance
    val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

}
