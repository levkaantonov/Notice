package levkaantonov.com.study.notice.databases.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import levkaantonov.com.study.notice.databases.DatabaseRepository
import levkaantonov.com.study.notice.models.AppNotice
import levkaantonov.com.study.notice.utils.*

class AppFirebaseRepository : DatabaseRepository {
    private val mAuth = FirebaseAuth.getInstance()
    private val mDbReference = FirebaseDatabase
        .getInstance()
        .reference
        .child(mAuth.currentUser?.uid.toString())

    override val allNotes: LiveData<List<AppNotice>> = AllNotisesLiveData()

    override suspend fun insert(notice: AppNotice, onSuccess: () -> Unit) {
        val id = mDbReference.push().key.toString()
        val mapNotises = hashMapOf<String, Any>()
        mapNotises[ID_FB] = id
        mapNotises[NAME] = notice.name
        mapNotises[TEXT] = notice.text

        mDbReference.child(id).updateChildren(mapNotises)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
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