package levkaantonov.com.study.notice.databases

import androidx.lifecycle.LiveData
import levkaantonov.com.study.notice.models.AppNotice

interface DatabaseRepository {
    val allNotes: LiveData<List<AppNotice>>
    suspend fun insert(notice: AppNotice, onSuccess: () -> Unit)
    suspend fun delete(notice: AppNotice, onSuccess: () -> Unit)

    fun connectToDB(onSuccess: () -> Unit, onError: (String) -> Unit) {}
    fun signOut() {}
}