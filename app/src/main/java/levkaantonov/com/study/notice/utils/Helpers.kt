package levkaantonov.com.study.notice.utils

import android.widget.Toast

fun showMessage(str: String){
    Toast.makeText(APP_ACTIVITY, str, Toast.LENGTH_SHORT).show()
}