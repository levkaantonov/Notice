package levkaantonov.com.study.notice.utils

import levkaantonov.com.study.notice.MainActivity
import levkaantonov.com.study.notice.databases.DatabaseRepository

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DatabaseRepository

const val TYPE_DB = "type_DATABASE"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"


lateinit var EMAIL: String
lateinit var PASSWORD: String

