package levkaantonov.com.study.notice.databases.room

import androidx.lifecycle.LiveData
import androidx.room.*
import levkaantonov.com.study.notice.models.AppNotice

@Dao
interface AppRoomDao {
    @Query("select * from Notises")
    fun getAllNotices():LiveData<List<AppNotice>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert (notice: AppNotice)

    @Delete
    suspend fun  delete(notice: AppNotice)
}