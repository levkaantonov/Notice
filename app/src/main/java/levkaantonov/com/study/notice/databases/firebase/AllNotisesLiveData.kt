package levkaantonov.com.study.notice.databases.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import levkaantonov.com.study.notice.models.AppNotice

class AllNotisesLiveData : LiveData<List<AppNotice>>() {
    private val mAuth = FirebaseAuth.getInstance()
    private val mDbReference = FirebaseDatabase
        .getInstance()
        .reference
        .child(mAuth.currentUser?.uid.toString())

    override fun onActive() {
        mDbReference.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        mDbReference.removeEventListener(listener)
        super.onInactive()
    }

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            value = snapshot.children.map {
                it.getValue(AppNotice::class.java) ?: AppNotice()
            }
        }

        override fun onCancelled(error: DatabaseError) {

        }
    }
}