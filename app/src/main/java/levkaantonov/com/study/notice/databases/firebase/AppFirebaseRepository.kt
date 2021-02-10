package levkaantonov.com.study.notice.databases.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import levkaantonov.com.study.notice.databases.DatabaseRepository
import levkaantonov.com.study.notice.models.AppNotice
import levkaantonov.com.study.notice.utils.*

class AppFirebaseRepository : DatabaseRepository {
    init {
        AUTH = FirebaseAuth.getInstance()
    }

    override val allNotes: LiveData<List<AppNotice>> = AllNotisesLiveData()

    override suspend fun insert(notice: AppNotice, onSuccess: () -> Unit) {
        val id = FB_REF_DB.push().key.toString()
        val mapNotises = hashMapOf<String, Any>()
        mapNotises[ID_FB] = id
        mapNotises[NAME] = notice.name
        mapNotises[TEXT] = notice.text

        FB_REF_DB.child(id).updateChildren(mapNotises)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun delete(notice: AppNotice, onSuccess: () -> Unit) {
        FB_REF_DB.child(notice.idFirebase).removeValue()
            .addOnSuccessListener {
                onSuccess()
            }.addOnFailureListener {
                showToast(it.message.toString())
            }
    }

    override fun connectToDB(onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (AppPreference.getInitUser()) {
            initRefs()
            onSuccess()
        } else {
            AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnSuccessListener {
                    initRefs()
                    onSuccess()
                }
                .addOnFailureListener {
                    AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                        .addOnSuccessListener {
                            initRefs()
                            onSuccess()
                        }
                        .addOnFailureListener { onError(it.message.toString()) }
                }
        }


    }

    private fun initRefs() {
        CURRENT_ID = AUTH.currentUser?.uid.toString()
        FB_REF_DB = FirebaseDatabase.getInstance().reference.child(CURRENT_ID)
    }

    override fun signOut() {
        AUTH.signOut()
    }
}