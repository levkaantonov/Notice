package levkaantonov.com.study.notice.databases.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import levkaantonov.com.study.notice.databases.DatabaseRepository
import levkaantonov.com.study.notice.models.AppNotice
import levkaantonov.com.study.notice.utils.EMAIL
import levkaantonov.com.study.notice.utils.PASSWORD

class AppFirebaseRepository : DatabaseRepository {
    private val mAuth = FirebaseAuth.getInstance()

    override val allNotes: LiveData<List<AppNotice>> = AllNotisesLiveData()

    override suspend fun insert(notice: AppNotice, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(notice: AppNotice, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun connectToDB(onSuccess: () -> Unit, onError: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onError(it.message.toString()) }
            }
    }

    override fun signOut() {
        mAuth.signOut()
    }
}