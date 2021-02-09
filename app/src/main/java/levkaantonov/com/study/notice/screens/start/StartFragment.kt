package levkaantonov.com.study.notice.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import levkaantonov.com.study.notice.R
import levkaantonov.com.study.notice.databinding.FragmentStartBinding
import levkaantonov.com.study.notice.utils.*

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: StartFragmentVewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(StartFragmentVewModel::class.java)
        mBinding.startBtnRoom.setOnClickListener {
            mViewModel.initDb(TYPE_ROOM){
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }

        mBinding.startBtnFb.setOnClickListener {
            mBinding.emailInput.visibility = View.VISIBLE
            mBinding.passwordInput.visibility = View.VISIBLE
            mBinding.startBtnLogin.visibility = View.VISIBLE

            mBinding.startBtnLogin.setOnClickListener {
                val email = mBinding.emailInput.text.toString()
                val password = mBinding.passwordInput.text.toString()
                if(email.isNotEmpty() && password.isNotEmpty()){
                    EMAIL = email
                    PASSWORD = password

                    mViewModel.initDb(TYPE_FIREBASE){
                        APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
                    }

                }else{
                    showToast(getString(R.string.bad_enter_toast))
                }
            }

        }
    }
}