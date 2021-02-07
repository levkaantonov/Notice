package levkaantonov.com.study.notice.databases.room

import androidx.lifecycle.LiveData
import levkaantonov.com.study.notice.databases.DatabaseRepository
import levkaantonov.com.study.notice.models.AppNotice

class AppRoomRepository(private val dao: AppRoomDao): DatabaseRepository {
    override val allNotes: LiveData<List<AppNotice>>
        get() = dao.getAllNotices()

    override suspend fun insert(notice: AppNotice, onSuccess: () -> Unit) {
        dao.insert(notice)
        onSuccess()
    }

    override suspend fun delete(notice: AppNotice, onSuccess: () -> Unit) {
        dao.delete(notice)
        onSuccess()
    }
}