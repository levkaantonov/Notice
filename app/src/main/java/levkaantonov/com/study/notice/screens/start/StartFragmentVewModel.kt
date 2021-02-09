package levkaantonov.com.study.notice.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import levkaantonov.com.study.notice.databases.firebase.AppFirebaseRepository
import levkaantonov.com.study.notice.databases.room.AppRoomDb
import levkaantonov.com.study.notice.databases.room.AppRoomRepository
import levkaantonov.com.study.notice.utils.REPOSITORY
import levkaantonov.com.study.notice.utils.TYPE_FIREBASE
import levkaantonov.com.study.notice.utils.TYPE_ROOM
import levkaantonov.com.study.notice.utils.showToast

class StartFragmentVewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application

    fun initDb(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                var dao = AppRoomDb.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDB({onSuccess()}, {showToast(it)})
            }
        }

    }
}