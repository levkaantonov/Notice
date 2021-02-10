package levkaantonov.com.study.notice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import levkaantonov.com.study.notice.databinding.ActivityMainBinding
import levkaantonov.com.study.notice.utils.APP_ACTIVITY
import levkaantonov.com.study.notice.utils.AppPreference

class MainActivity : AppCompatActivity() {
    lateinit var mToolbar: Toolbar
    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    val mBindig get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBindig.root)
        APP_ACTIVITY = this

        mToolbar = mBindig.toolbar
        navController = Navigation.findNavController(this, R.id.nav_host_frag)
        setSupportActionBar(mToolbar)
        title = getString(R.string.title)
        AppPreference.getPreference(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}