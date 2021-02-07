package levkaantonov.com.study.notice.screens.add_new_notice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import levkaantonov.com.study.notice.R
import levkaantonov.com.study.notice.databinding.FragmentAddNoticeBinding
import levkaantonov.com.study.notice.models.AppNotice
import levkaantonov.com.study.notice.utils.APP_ACTIVITY
import levkaantonov.com.study.notice.utils.showMessage

class AddNoticeFragment : Fragment() {

    private var _binding: FragmentAddNoticeBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: AddNoticeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoticeBinding.inflate(layoutInflater, container, false)
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
        mViewModel = ViewModelProvider(this).get(AddNoticeFragmentViewModel::class.java)
        mBinding.btnAddNotice.setOnClickListener {
            val name = mBinding.inputNameNotice.text.toString()
            val text = mBinding.inputTextNotice.text.toString()
            if(name.isEmpty()){
                showMessage(getString(R.string.toast_error_enter_name))
                return@setOnClickListener
            }
            mViewModel.insert(AppNotice(name =  name, text = text)){
                APP_ACTIVITY.mNavController.navigate(R.id.action_addNoticeFragment_to_mainFragment)
            }
        }
    }
}