package levkaantonov.com.study.notice.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import levkaantonov.com.study.notice.utils.REPOSITORY

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val allNotices = REPOSITORY.allNotes
    fun signOut(){
        REPOSITORY.signOut()
    }
}