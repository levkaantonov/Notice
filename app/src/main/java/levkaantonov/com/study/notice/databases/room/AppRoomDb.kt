package levkaantonov.com.study.notice.databases.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import levkaantonov.com.study.notice.models.AppNotice

@Database(entities = [AppNotice::class], version = 1)
abstract class AppRoomDb : RoomDatabase() {
    abstract fun getAppRoomDao(): AppRoomDao

    companion object {
        @Volatile
        private var db: AppRoomDb? = null

        @Synchronized
        fun getInstance(ctx: Context): AppRoomDb {
            if (db == null) {
                db = Room.databaseBuilder(
                    ctx,
                    AppRoomDb::class.java,
                    "db"
                ).build()
                return db as AppRoomDb
            }
            return db as AppRoomDb
        }
    }
}