package levkaantonov.com.study.notice.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import levkaantonov.com.study.notice.MainActivity
import levkaantonov.com.study.notice.databases.DatabaseRepository

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DatabaseRepository
lateinit var AUTH: FirebaseAuth
lateinit var FB_REF_DB: DatabaseReference

lateinit var CURRENT_ID:String
lateinit var EMAIL: String
lateinit var PASSWORD: String



const val TYPE_DB = "type_DATABASE"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
const val ID_FB = "idFirebase"
const val NAME = "name"
const val TEXT = "text"
