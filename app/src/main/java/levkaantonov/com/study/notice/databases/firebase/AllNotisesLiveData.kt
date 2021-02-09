package levkaantonov.com.study.notice.databases.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import levkaantonov.com.study.notice.models.AppNotice
import levkaantonov.com.study.notice.utils.FB_REF_DB

class AllNotisesLiveData : LiveData<List<AppNotice>>() {
    override fun onActive() {
        FB_REF_DB.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        FB_REF_DB.removeEventListener(listener)
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