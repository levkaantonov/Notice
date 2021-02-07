package levkaantonov.com.study.notice.screens.add_new_notice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import levkaantonov.com.study.notice.models.AppNotice
import levkaantonov.com.study.notice.utils.REPOSITORY

class AddNoticeFragmentViewModel(application: Application) : AndroidViewModel(application) {
    fun insert(notice: AppNotice, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(notice){
                onSuccess()
            }
        }
}