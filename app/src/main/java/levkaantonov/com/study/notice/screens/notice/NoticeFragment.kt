package levkaantonov.com.study.notice.screens.notice

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import levkaantonov.com.study.notice.R
import levkaantonov.com.study.notice.databinding.FragmentMainBinding
import levkaantonov.com.study.notice.databinding.FragmentNoticeBinding
import levkaantonov.com.study.notice.models.AppNotice
import levkaantonov.com.study.notice.screens.main.MainAdapter
import levkaantonov.com.study.notice.screens.main.MainFragmentViewModel
import levkaantonov.com.study.notice.utils.APP_ACTIVITY

class NoticeFragment : Fragment() {

    private var _binding: FragmentNoticeBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: NoticeFragmentViewModel
    private lateinit var mCurrentNotice: AppNotice

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        mBinding.noticeName.text = mCurrentNotice.name
        mBinding.noticeText.text = mCurrentNotice.text
        mViewModel = ViewModelProvider(this).get(NoticeFragmentViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.notice_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_delete -> {
                mViewModel.delete(mCurrentNotice){
                    APP_ACTIVITY.navController.navigate(R.id.action_noticeFragment_to_mainFragment)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoticeBinding.inflate(layoutInflater, container, false)
        mCurrentNotice = arguments?.getSerializable("notice") as AppNotice
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}