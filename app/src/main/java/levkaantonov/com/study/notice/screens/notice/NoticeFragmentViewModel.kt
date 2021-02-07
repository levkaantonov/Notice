package levkaantonov.com.study.notice.screens.notice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import levkaantonov.com.study.notice.models.AppNotice
import levkaantonov.com.study.notice.utils.REPOSITORY

class NoticeFragmentViewModel(application: Application) : AndroidViewModel(application) {
    fun delete(notice: AppNotice, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.delete(notice){
                onSuccess()
            }
        }
}